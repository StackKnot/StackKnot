package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.models.UserSetProg;
import com.codeup.stackknot.repositories.CardRepository;
import com.codeup.stackknot.repositories.SetRepository;
import com.codeup.stackknot.repositories.UserRepository;
//import com.codeup.stackknot.services.EmailService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.codeup.stackknot.repositories.UserSetProgRepository;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private UserRepository usersDao;
    private CardRepository cardsDao;
    private SetRepository setsDao;
    private PasswordEncoder passwordEncoder;
    private UserSetProgRepository userSetProgDao;

    public UserController(UserRepository usersDao, CardRepository cardsDao, SetRepository setsDao, PasswordEncoder passwordEncoder, UserSetProgRepository userSetProgDao) {
        this.usersDao = usersDao;
        this.cardsDao = cardsDao;
        this.setsDao = setsDao;
        this.passwordEncoder = passwordEncoder;
        this.userSetProgDao = userSetProgDao;
    }

    //USER REGISTRATION
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    // CHECK IF USERNAME OR EMAIL ALREADY EXISTS
    public boolean checkIfUserExist(String username) {
        return usersDao.findByUsername(username) != null;
    }

    public boolean checkIfEmailExists(String email) {
        return usersDao.findByEmail(email) != null;
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid @ModelAttribute User user, Errors validation, Model model) throws AlreadyBuiltException {
//        emailService.prepareAndSend(user, "Registration Confirmation", "Welcome To StacKKnot, Thank You For Registering!");
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            return "users/sign-up";
        }

        if (checkIfUserExist(user.getUsername())) {
            return "redirect:/sign-up/?failusername";
        } else if (checkIfEmailExists(user.getEmail())) {
            return "redirect:/sign-up/?failemail";
        } else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            user.setAdmin(false);
            usersDao.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/profile")
    public String profile() {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/profile/" + loggedInUser.getUsername();
    }

    //INDIVIDUAL PROFILE PAGES, THIS WILL BE LOCKED DOWN LATER WHEN WE ARE ACTUALLY CHECKING AGAINST THE SESSION
    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model model) {
        User user = usersDao.findByUsername(username);
        List<Set> sets = setsDao.findAllByUserId(user.getId());
        List<UserSetProg> setsStudied = userSetProgDao.findAllByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("sets", sets);
        model.addAttribute("setsStudied", setsStudied);
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
