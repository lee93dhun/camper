package com.camper.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddCampDto {

    @NotBlank(message = "캠핑장 이름은 필수 입력 값입니다.")
    private String campName;

    @NotBlank(message = "캠핑장 주소는 필수 입력 값입니다.")
    private String address;

    @NotBlank(message = "사업자 번호는 필수 입력 값입니다.")
    private String bNumber;

    @NotBlank(message = "대표이미지를 등록해주세요.")
    private String campImg;

    @NotBlank(message = "캠핑장 지역을 선택해주세요.")
    private String campArea;

    @NotBlank(message = "캠핑장 주변환경을 선택해주세요.")
    private String surrounding;

    private int checkStatus;
}
