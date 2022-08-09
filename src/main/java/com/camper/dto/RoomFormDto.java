package com.camper.dto;

import com.camper.constant.RoomStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoomFormDto {

    private Long id;

    @NotBlank(message = "객실 이름을 입력해주세요.")
    private String roomName;    // 객실이름

    @NotBlank(message = "객실 가격을 입력해주세요.")
    private int roomPrice;  // 객실가격

    @NotBlank(message = "객실 정보를 입력해주세요.")
    private String rommInfo;    // 객실정보

    private RoomStatus roomStatus;

    private List<RoomImgDto> roomImgDtoList = new ArrayList<>(); // 객실 이미지 정보

    private List<Long> RoomImgIds = new ArrayList<>(); // 객실 이미지 아이디
}
