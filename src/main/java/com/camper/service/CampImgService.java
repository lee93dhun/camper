package com.camper.service;

import com.camper.entity.CampImg;
import com.camper.repository.CampImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class CampImgService {

    @Value("${campImgLocation}") //application.properties에 itemImgLocation
    private String campImgLocation;
    private final CampImgRepository campImgRepository;
    private final FileService fileService;
    public void saveCampImg(CampImg campImg, MultipartFile campImgFile) throws Exception {
        String oriImgName = campImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(campImgLocation, oriImgName, campImgFile.getBytes());
            imgUrl = "/images/item1/"+imgName;
        }

        //캠프 이미지 정보 저장
        //oriImgName : 캠프 이미지 파일 원래 이름
        //imgName : 실제 로컬에 저장된 캠프 이미지 파일 이름
        //imgUrl : 로컬에 저장된 캠프 이미지 파일을 불러오는 경로
        campImg.updateCampImg(oriImgName, imgName, imgUrl);
        campImgRepository.save(campImg);
    }
}