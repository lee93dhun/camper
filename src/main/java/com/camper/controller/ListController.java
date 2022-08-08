package com.camper.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ListController {

    @GetMapping(value = "/campSearch")
    public String campForm() {
        return "list/campSearch";
    }

    @GetMapping(value = "/roomSearch")
    public String roomForm() {
        return "list/roomSearch";
    }
}
