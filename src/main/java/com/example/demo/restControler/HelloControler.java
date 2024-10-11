package com.example.demo.restControler;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/hello")
public class HelloControler {

    @Autowired
    private HelloService helloSvc;

    @GetMapping
    public String direHello() {
        return helloSvc.salutations();
    }

}
