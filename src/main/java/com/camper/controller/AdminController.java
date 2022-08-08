package com.camper.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {
    @GetMapping(value = "/permit")
    public String permit(Model model){
        return "/Admin/permit";
    }
}
