package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.Subject;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.repositories.*;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SetController {
    //Dependency Injection
    private SetRepository setDao;
    private CardRepository cardDao;
    private SubjectRepository subjectDao;
    private UserRepository userDao;
    private ProgressionRepository progressionDao;
    private UserSetProgRepository userSetProgDao;

    public SetController(SetRepository setDao, CardRepository cardDao, SubjectRepository subjectDao, UserRepository userDao, ProgressionRepository progressionDao, UserSetProgRepository userSetProgDao) {
        this.setDao = setDao;
        this.cardDao = cardDao;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.progressionDao = progressionDao;
        this.userSetProgDao = userSetProgDao;
    }

    // CREATE SET
    @GetMapping("/sets/create")
    public String createSetForm(Model model) {
        model.addAttribute("newSet", new Set());
        model.addAttribute("subjects", subjectDao.findAll());
        return "sets/create";
    }

    @PostMapping("/sets/create")
    public String createSet(@ModelAttribute Set set, @RequestParam(name = "subjectId") long subjectId) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Subject subject = subjectDao.getById(subjectId);
        set.setUser(loggedInUser);
        set.setSubject(subject);
        setDao.save(set);
        return "redirect:../cards/create/" + set.getId();
    }

//    LIKE A SET
    @PostMapping("/sets/{id}/like")
    public String like(@PathVariable long id) {
        Set setToLike = setDao.getById(id);
        setToLike.setLikes(setToLike.getLikes() + 1);
        setDao.save(setToLike);
        return "redirect:/sets/" + setToLike.getId();
    }

    // SHOW SPECIFIC SET BY ID
    @GetMapping("/sets/{id}")
    public String setById(@PathVariable long id, Model model, User user) {
        model.addAttribute("singleSet", setDao.getById(id));
        model.addAttribute("cards", cardDao.findAllBySetId(id));
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", loggedInUser);
        } else {
            User guestuser = new User();
            user.setId(0);
            model.addAttribute("user", guestuser);
        }
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
    public String editSetFrom(@PathVariable long id, Model model, @ModelAttribute User user) {
        model.addAttribute("set", setDao.getById(id));
        model.addAttribute("subjects", subjectDao.findAll());
        return "sets/edit";

    }

    @PostMapping("/sets/{id}/edit")
    public String submitEdit(@ModelAttribute Set set, @PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            set.setUser(loggedInUser);
            setDao.save(set);
            return "redirect:/sets";
    }

    // DELETE SPECIFIC SET BY ID

    @GetMapping("/sets/{id}/delete")
    public String confirmDelete(@PathVariable long id, Model model) {
        Set set = setDao.getById(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (set.getUser().getId() == loggedInUser.getId()) {
            model.addAttribute("set", set);
            return "sets/delete";
        } else {
            return "redirect:/sets";
        }
    }

    @PostMapping("/sets/{id}/delete")
    public String deleteSet(@PathVariable long id) {
        setDao.deleteById(id);
        return "redirect:/sets";
    }

    //SEARCH SETS BY TITLE/DESCRIPTION
    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        List<Set> searchResult = setDao.search(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);
        return "sets/search";
    }
}
