package com.camper.dto;

import com.camper.constant.RoomStatus;
import com.camper.entity.Room;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoomFormDto {

    private Long id;

    @NotBlank(message = "객실 이름을 입력해주세요.")
    private String roomName;    // 객실이름

    @NotNull(message = "객실 가격을 입력해주세요.")
    private Integer roomPrice;  // 객실가격

    @NotBlank(message = "객실 정보를 입력해주세요.")
    private String roomInfo;    // 객실정보

//    @NotBlank(message = "캠핑 유형을 선택해주세요.")
//    private String campType;

    private RoomStatus roomStatus;

    private List<RoomImgDto> roomImgDtoList = new ArrayList<>(); // 객실 이미지 정보

    private List<Long> RoomImgIds = new ArrayList<>(); // 객실 이미지 아이디

    private static ModelMapper modelMapper = new ModelMapper();

    public Room createRoom(){
        return modelMapper.map(this, Room.class);    // RoomFormDto -> Room 연결
    }

}
