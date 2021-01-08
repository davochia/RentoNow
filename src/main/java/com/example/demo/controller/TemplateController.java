package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@Controller
//@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLonginView(){
        return "login";
    }

    @GetMapping("apartments")
    public String getApartments(){
        return "apartments";
    }
}
