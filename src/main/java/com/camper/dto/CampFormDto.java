package com.camper.dto;

import com.camper.constant.Permission;
import com.camper.entity.Camp;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CampFormDto {

    private Long id;

    @NotBlank(message = "캠핑장 이름을 입력해주세요.")
    private String campName; // 캠핑장 이름

    @NotBlank(message = "캠핑장 주소를 입력해주세요.")
    private String address; // 캠핑장 주소

    @NotBlank(message = "사업자 번호를 입력해주세요.")
    private String bNumber; // 사업자 번호

    @NotBlank(message = "지역을 선택해주세요.")
    private String campArea;// 지역

    @NotBlank(message = "주변환경을 선택해주세요.")
    private String surroundings; // 주변환경

    @NotBlank(message = "입실시간을 입력해주세요.")
    private String inTime; // 입실시간

    @NotBlank(message = "퇴실시간을 입력해주세요.")
    private String outTime; // 퇴실시간

    @NotBlank(message = "캠핑장 정보를 입력해주세요.")
    private String campInfo; // 캠핑장 정보

    private CampImgDto campImgDto;

    private Long campImgId;

    private Permission permission; //승인 허가

    private static ModelMapper modelMapper = new ModelMapper();

    public Camp createCamp(){
        return modelMapper.map(this, Camp.class);   // CampFormDto -> Camp연결
    }
    public static CampFormDto of(Camp camp){
        return modelMapper.map(camp, CampFormDto.class);
    }
}
