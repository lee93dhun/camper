package com.camper.controller;

import com.camper.dto.CampFormDto;
import com.camper.dto.RoomFormDto;
import com.camper.service.CampService;
import com.camper.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor    // final이나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class CampController {

    private final CampService campService;

    private final RoomService roomService;

    /* 캠핑장 등록 페이지 불러오기 컨트롤러 */
    @GetMapping(value = "/campForm")
    public String campForm(Model model){
        model.addAttribute("campFormDto", new CampFormDto());
        return "camp/campForm";
    }

    @PostMapping(value = "/campForm")
    public String campNew(@Valid CampFormDto campFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("campImgFile") MultipartFile campImgFile) {


        if(bindingResult.hasErrors()) {

            return "camp/campForm";
        }
        if(campImgFile.isEmpty() && campFormDto.getId() == null){
            model.addAttribute("errorMessage", "캠핑장 대표 이미지를 등록해주세요.");
            return "camp/campForm";
        }
        try{
            campService.saveCamp(campFormDto, campImgFile);
        }catch (Exception e){
            model.addAttribute("errorMessage", "캠핑장 등록중 에러가 발생하였습니다.");
            return "camp/campForm";
        }
        return "redirect:/";

    }

    //  객실 등록 페이지 불러오기
    @GetMapping(value = "/roomForm")
    public String roomForm(Model model){
        model.addAttribute("roomFormDto", new RoomFormDto());
        return "camp/roomForm";
    }
    @PostMapping(value = "/roomForm")
    public String roomNew(@Valid RoomFormDto roomFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("roomImgFile") List<MultipartFile> roomImgFileList) {
        if (bindingResult.hasErrors()) {
            return "camp/roomForm";
        }

        if (roomImgFileList.get(0).isEmpty() && roomFormDto.getId() == null) {
            model.addAttribute("errorMessage",
                    "첫번째 객실 이미지는 필수 입력 값입니다.");
            return "camp/roomForm";
        }

        try {
            roomService.saveItem(roomFormDto, roomImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage",
                    "객실 등록 중 에러가 발생하였습니다.");
            return "camp/roomForm";
        }
        return "redirect:/";
    }



    // 예약현황 페이지 불러오기
    @GetMapping(value = "/reservation")
    public String reservation(Model model){
        return "/camp/reservation";
    }
}