package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String getCityList(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }
}
