package com.camper.controller;

import com.camper.dto.AddCampDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class CampController {

    /* 캠핑장 등록 페이지 불러오기 컨트롤러 */
    @GetMapping(value = "/addCamp")
    public String addCamp(Model model){
        model.addAttribute("addCampDto", new AddCampDto());
        return "/camp/addCamp";
    }

    @PostMapping(value = "/addCamp")
    public String addCamp(@Valid AddCampDto addCampDto, BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) { // 각 변수명 체크 문제가 생기면 호출
            return "member/addCamp";
        }
//        try {
//            Member member = Member.createMember(addCampDto);
//            campService.saveCamp(camp);
//        } catch (IllegalStateException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "member/addCamp";
//        }
        return "redirect:/";

    }

    @GetMapping(value = "/addRoom")
    public String addRoom(Model model){
        return "/camp/addRoom";
    }


    @GetMapping(value = "/reservation")
    public String reservation(Model model){
        return "/camp/reservation";
    }
}
