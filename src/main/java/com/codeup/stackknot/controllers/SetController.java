package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.Subject;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.repositories.*;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SetController {
    //Dependency Injection
    private SetRepository setDao;
    private CardRepository cardDao;
    private SubjectRepository subjectDao;
    private UserRepository userDao;
    private ProgressionRepository progressionDao;

    public SetController(SetRepository setDao, CardRepository cardDao, SubjectRepository subjectDao, UserRepository userDao, ProgressionRepository progressionDao) {
        this.setDao = setDao;
        this.cardDao = cardDao;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.progressionDao = progressionDao;
    }

    // CREATE SET
    @GetMapping("/sets/create")
    public String createSetForm(Model model) {
        model.addAttribute("newSet", new Set());
        model.addAttribute("subjects", subjectDao.findAll());
        return "sets/create";
    }

    @PostMapping("/sets/create")
    public String createSet(@ModelAttribute Set set, @ModelAttribute Subject subject) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        set.setUser(loggedInUser);
        set.setUser(userDao.getById(1L));
//        set.setSubject(subject);
        setDao.save(set);
        return "redirect:../cards/create";

    }

    // SHOW SPECIFIC SET BY ID
    @GetMapping("/sets/{id}")
    public String setById(@PathVariable long id, Model model) {
        model.addAttribute("singleSet", setDao.getById(id));
        model.addAttribute("cards", cardDao.findAllBySetId(id));
        return "sets/show";
    }

    // SHOW ALL SETS THAT HAVE BEEN CREATED
    @GetMapping("/sets")
    public String allSets(Model model) {
        model.addAttribute("allSets", setDao.findAll());
        return "sets/index";
    }

    // EDIT SPECIFIC SET BY ID
    @GetMapping("/sets/{id}/edit")
    public String editSetFrom(@PathVariable long id, Model model) {
        Set set = setDao.getById(id);
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(1L);
        if (set.getUser().getId() == loggedInUser.getId()) {
            model.addAttribute("set", set);
            return "sets/edit";
        } else {
            return "redirect:/sets";
        }
    }

    @PostMapping("/sets/{id}/edit")
    public String submitEdit(@ModelAttribute Set set, @PathVariable long id) {
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(1L);
        set.setUser(loggedInUser);
        setDao.save(set);
        return "redirect:/sets";
    }

    // DELETE SPECIFIC SET BY ID

    @GetMapping("/sets/{id}/delete")
    public String confirmDelete(@PathVariable long id, Model model){
        Set set = setDao.getById(id);
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.getById(1L);
        if (set.getUser().getId() == loggedInUser.getId()) {
            model.addAttribute("set", set);
            return "sets/delete";
        } else {
            return "redirect:/sets";
        }
    }

    @PostMapping("/sets/{id}/delete")
    public String deleteSet(@ModelAttribute Set set, @PathVariable long id) {
        User loggedInUser = userDao.getById(1L);
        set.setUser(loggedInUser);
        setDao.deleteById(id);
        return "redirect:/sets";
    }
}
