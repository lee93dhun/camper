package com.camper.controller;

import com.camper.dto.MemberFormDto;
import com.camper.entity.Member;
import com.camper.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    /* 로그인 페이지 불러오기 컨트롤러 */
    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/login";
    }


    /* 회원가입 페이지 불러오기 컨트롤러 */

    @GetMapping(value = "/join")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/join";
    }

    @PostMapping(value = "/join")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) { // 각 변수명 체크 문제가 생기면 호출 이름 X 이메일 형식 X
            return "member/join";
        }
        try {
            
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/join";
        }
        return "redirect:/";

    }

    // 내 정보
    @GetMapping(value = "/myInfo")
    public String myInfo(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "/member/myInfo";
    }

    // 찜 목록
    @GetMapping(value = "/likeCamp")
    public String likeCamp(){
        return "/member/likeCamp";
    }
}
