package com.camper.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {

    /* 메인문 컨트롤러 */
    @GetMapping("/")
    public String main(){

        return "main";
    }


}
