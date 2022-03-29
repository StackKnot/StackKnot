package com.codeup.stackknot.controllers;

import com.codeup.stackknot.Spring.Twilio.EmailRequest;
import com.codeup.stackknot.Spring.Twilio.service.EmailService;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {
    private final EmailService emailSvc;

    public HomeController(EmailService emailService) {
        this.emailSvc=emailService;
    }
    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }


    @GetMapping("/about-us")
    public String showAboutUs() {
        return "about-us";
    }


    @GetMapping("/contact-form")
    public String showContactAnExpert(Model model) {
        model.addAttribute("emailRequest", new EmailRequest());
        return "contact-form";
    }

    @PostMapping("/contact-form")
    public String submitContactForm(@RequestBody EmailRequest emailrequest) {
        System.out.println(emailSvc.sendemail(emailrequest));
        return "redirect:/"; }



}
