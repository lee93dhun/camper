package com.camper.controller;

import com.camper.dto.MemberFormDto;
import com.camper.entity.Member;
import com.camper.repository.MemberRepository;
import com.camper.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

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
    // 내 정보
    @GetMapping(value = "/myInfo")
    public String myInfo(Principal principal, Model model){

        try {
            MemberFormDto memberFormDto = memberService.getMemberInfo(principal.getName()); //현재 접속중인 이메일을 가지고 감.
            model.addAttribute("memberFormDto", memberFormDto);
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage","존재하지 않는 유저입니다.");
            //model.addAttribute("memberFormDto",new MemberFormDto());
            return "redirect:/";
        }
        return "member/myInfo";
        //model.addAttribute("memberFormDto", new MemberFormDto());
        //return "/member/myInfo";
    }

    @PostMapping(value = "/myInfo")
    public String ChangemyInfo(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Principal principal,
                               Model model){
        if (bindingResult.hasErrors()) { // 각 변수명 체크 문제가 생기면 호출 이름 X 이메일 형식 X
            return "member/myInfo";
        }

        try {
            String name = principal.getName(); //현재 접속중인 유저의 이메일을 추출
            Member oldMember = memberRepository.findByEmail(name); //현재 접속중인 유저 member 객체를 가져옴
            //Member newMember =
            //Member.updateMember(memberFormDto, passwordEncoder,oldMember); //수정한 값으로 업데이트 한 후애 맴버 객체 탄생 !
            //memberService.updateMember(newMember); //
            memberService.updateMember(memberFormDto,passwordEncoder,oldMember);

        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", "회원정보 수정이 실패하였습니다.");
            return "member/myInfo";
        }
        return "redirect:/";

    }

    // 찜 목록
    @GetMapping(value = "/likeCamp")
    public String likeCamp(){
        return "/member/likeCamp";
    }

    // 내 예약 확인
    @GetMapping(value = "/bookingList")
    public String bookingList(){
        return "/member/bookingList";
    }
}
