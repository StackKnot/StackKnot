package com.codeup.stackknot.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.codeup.stackknot.repositories.UserRepository;
import com.codeup.stackknot.repositories.WhiteboardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cloudinary.*;

@Controller
public class WhiteboardController {

    // DEPENDENCY INJECTION
    private UserRepository userDao;
    private WhiteboardRepository boardDao;

    // CLOUDINARY CONFIG


    @GetMapping("/whiteboard")
    public String showEditor(Model model) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "smith-gary",
                "api_key", "883556972268276",
                "api_secret", "ixEGdvlRBS2RAKCiYWZcQ2340Oc",
                "secure", true));
    cloudinary.url().imageTag("boulder.jpg", Cloudinary.asMap("alt", "boulder image"));

        model.addAttribute("url", boardDao.getById(1L).getJavaURL());
        return "whiteboard/whiteboard";
    }
}
