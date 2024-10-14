package com.example.demo.controller;

import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private CityService citySvc;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("message", "Hello World!");
        model.addAttribute("cities", citySvc.extractAllCities());
        return "index";
    }
}
