package com.camper.service;

import com.camper.dto.CampFormDto;
import com.camper.entity.Camp;
import com.camper.entity.CampImg;
import com.camper.repository.CampImgRepository;
import com.camper.repository.CampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CampService {

    private final CampRepository campRepository;

    private final CampImgService campImgService;

    private final CampImgRepository campImgRepository;

    public Long saveCamp(CampFormDto campFormDto, MultipartFile itemImgFile) throws Exception{

        //캠프 등록
        Camp camp = campFormDto.createCamp();
        campRepository.save(camp);

        //이미지 등록
        CampImg campImg = new CampImg();
        campImg.setCamp(camp);
        campImgService.saveCampImg(campImg, itemImgFile);

        return camp.getId();

    }
}