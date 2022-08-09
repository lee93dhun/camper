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

    @Value("${campImgLocation}") //application.properties에 campImgLocation
    private String campImgLocation;
    private final CampImgRepository campImgRepository;
    private final FileService fileService;

    public void saveCampImg(CampImg campImg, MultipartFile campImgFile) throws Exception {

        String oriImgName = campImgFile.getOriginalFilename(); // 오리지날 이미지 경로
        String imgName = "";
        String imgUrl = "";
        System.out.println(oriImgName);
        //파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) { // oriImgName 문자열로 비어 있지 않으면 실행
            imgName = fileService.uploadFile(campImgLocation, oriImgName,
                    campImgFile.getBytes());
            System.out.println(imgName);
            imgUrl = "/images/campItem/" + imgName;
        }
        //상품 이미지 정보 저장
        // oriImgName : 상품 이미지 파일의 원래 이름
        // imgName : 실제 로컬에 저장된 상품 이미지 파일의 이름
        // imgUrl :  로컬에 저장된 상품 이미지 파일을 불러오는 경로
        campImg.updateCampImg(oriImgName, imgName, imgUrl);
        campImgRepository.save(campImg);
    }
}
