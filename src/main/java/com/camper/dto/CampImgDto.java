package com.camper.dto;

import com.camper.entity.CampImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CampImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private static ModelMapper modelMapper = new ModelMapper();

    public static CampImgDto of(CampImg campImg){
        return modelMapper.map(campImg,CampImgDto.class);
    }

}
