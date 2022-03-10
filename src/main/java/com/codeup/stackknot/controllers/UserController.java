package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.repositories.CardRepository;
import com.codeup.stackknot.repositories.SetRepository;
import com.codeup.stackknot.repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private UserRepository usersDao;
    private CardRepository cardsDao;
    private SetRepository setsDao;


    public UserController(UserRepository usersDao, CardRepository cardsDao, SetRepository setsDao) {
        this.usersDao = usersDao;
        this.cardsDao = cardsDao;
        this.setsDao = setsDao;
    }


    // LOGIN MAPPING, DOESNT DO MUCH WILL MOVE THIS TO AUTHENTICATION CONTROLLER ONCE WE ARE CLOSER TO FINISHED PRODUCT AND

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }


    //USER REGISTRATION
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        usersDao.save(user);
        return "redirect:/login";
    }

    //INDIVIDUAL PROFILE PAGES, THIS WILL BE LOCKED DOWN LATER WHEN WE ARE ACTUALLY CHECKING AGAINST THE SESSION
    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model model) {
        User user = usersDao.findByUsername(username);
        List<Set> sets = setsDao.findAllByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("sets", sets);
        return "users/profile";
    }

    //EDITING OF PROFILE
    @GetMapping("/profile/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        User user = usersDao.getById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/profile/{id}/edit")
    public String submitEdit(@ModelAttribute User changes) {
        User userToEdit = usersDao.getById(changes.getId());

        userToEdit.setUsername(changes.getUsername());
        userToEdit.setEmail(changes.getEmail());
        userToEdit.setFirstName(changes.getFirstName());
        userToEdit.setLastName(changes.getLastName());
        usersDao.save(userToEdit);
        return "redirect:/profile/" + userToEdit.getUsername();
    }


}
