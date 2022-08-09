package com.camper.controller;

import com.camper.dto.CampFormDto;
import com.camper.entity.Camp;
import com.camper.service.CampService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor    // final이나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class CampController {

    private final CampService campService;

    /* 캠핑장 등록 페이지 불러오기 컨트롤러 */
    @GetMapping(value = "/host/camp/new")
    public String campForm(Model model){
        model.addAttribute("campFormDto", new CampFormDto());
        return "camp/campForm";
    }

    @PostMapping(value = "/host/camp/new")
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

        return "camp/roomForm";
    }

// 예약현황 페이지 불러오기
    @GetMapping(value = "/reservation")
    public String reservation(Model model){

        return "/camp/reservation";
    }
}
