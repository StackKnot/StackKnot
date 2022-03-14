package com.codeup.stackknot.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    public String showLoginForm() {
        return "about-us";
    }

    @GetMapping("/contact-form")
    public String showContactAnExpert() {
        return "/contact-form";
    }



}
