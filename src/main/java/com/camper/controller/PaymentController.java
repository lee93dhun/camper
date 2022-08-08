package com.camper.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PaymentController {

    @GetMapping(value = "/pay")
    public String loginMember(){
        return "/payment/pay";
    }
}
