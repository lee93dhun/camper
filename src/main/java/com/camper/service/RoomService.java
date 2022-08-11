package com.camper.service;

import com.camper.dto.RoomFormDto;
import com.camper.entity.Camp;
import com.camper.entity.Member;
import com.camper.entity.Room;
import com.camper.entity.RoomImg;
import com.camper.repository.MemberRepository;
import com.camper.repository.RoomImgRepository;
import com.camper.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomImgService roomImgService;
    private final RoomImgRepository roomImgRepository;
    private final MemberRepository memberRepository;

    public Long saveRoom(RoomFormDto roomFormDto, List<MultipartFile> roomImgFileList, String email) throws Exception {

        // 객실등록
        Room room = roomFormDto.createRoom();
        Member member = memberRepository.findByEmail(email);
        Camp camp = member.getCamp();
        room.setCamp(camp);
        roomRepository.save(room);
        // 이미지 등록
        for (int i = 0; i < roomImgFileList.size(); i++) {
            RoomImg roomImg = new RoomImg();
            roomImg.setRoom(room);
            if (i == 0) {
                roomImg.setRepImgYn("Y");
            } else {
                roomImg.setRepImgYn("N");
            }
            roomImgService.saveRoomImg(roomImg, roomImgFileList.get(i));
        }
        return room.getId();
    }
//
//    @Transactional(readOnly = true)// 읽기전용 -> 더티체킹(변경감지) -> 성능향상
//    public RoomFormDto getItemDtl(Long itemId){
//        List<RoomImg> roomImgList = roomImgRepository.findByItemIdOrderByIdAsc(itemId); //DB에서 데이터를 가지고 옵니다.
//        List<ItemImgDto> itemImgDtoList = new ArrayList<>(); //왜 DTO 만들었나요??
//
//        for(ItemImg itemimg : itemImgList){ // ItemImg 엔티티를 ItemImgDto 객체를 만들어서 리스트에 추가
//            ItemImgDto itemImgDto = ItemImgDto.of(itemimg);
//            itemImgDtoList.add(itemImgDto);
//        }
//        //Item 엔티티 조회 -> 조회X EntityNotFoundException 실행
//        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
//        ItemFormDto itemFormDto = ItemFormDto.of(item);
//        itemFormDto.setItemImgDtoList(itemImgDtoList);
//        return itemFormDto;
//    }
//

}
