package com.codeup.stackknot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }


    @GetMapping("/about-us")
    public String showAboutUs() {
        return "about-us";
    }


    @GetMapping("/contact-form")
    public String showContactAnExpert() {
        return "/contact-form";
    }



}
