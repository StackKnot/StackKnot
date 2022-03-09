package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.User;
import com.codeup.stackknot.repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository usersDao;


    public UserController(UserRepository usersDao) {
        this.usersDao = usersDao;

    }

    @GetMapping("/homepage")
    public String showHomePage() {
        return "/homepage";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        usersDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model model) {
        User user = usersDao.findByUsername(username);
        model.addAttribute("user", user);
        return "users/profile";
    }



}
