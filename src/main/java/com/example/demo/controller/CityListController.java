package com.example.demo.controller;

import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityListController {


    @Autowired
    private CityService citySvc;

    @GetMapping("/citylist")
    public String getCityList(Model model) {
        model.addAttribute("message", "City list");
        model.addAttribute("cities", citySvc.extractAllCities());
        return "city-list";
    }
}
