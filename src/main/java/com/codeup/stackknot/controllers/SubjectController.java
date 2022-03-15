package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Subject;
import com.codeup.stackknot.repositories.CardRepository;
import com.codeup.stackknot.repositories.SetRepository;
import com.codeup.stackknot.repositories.SubjectRepository;
import com.codeup.stackknot.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SubjectController {
    private SetRepository setsDao;
    private CardRepository cardsDao;
    private UserRepository usersDao;
    private SubjectRepository subjectsDao;

    public SubjectController(SetRepository setsDao, CardRepository cardsDao, UserRepository usersDao, SubjectRepository subjectsDao) {
        this.setsDao = setsDao;
        this.cardsDao = cardsDao;
        this.usersDao = usersDao;
        this.subjectsDao = subjectsDao;
    }

    // THIS IS THE SUBJECT INDEX PAGE< SHOWS ALL THE SUBJECTS AND THEIR CORRESPONDING SETS
    @GetMapping("/subjects/index")
    public String allSubjects(Model model) {
        model.addAttribute("subjects", subjectsDao.findAll());
        return "subjects/index";
    }

    //INDIVIDUAL SUBJECT AND THEIR CORRESPONDING SETS
    @GetMapping("/subjects/{title}")
    public String showSubject(@PathVariable String title, Model model) {
        Subject subject = subjectsDao.findByTitle(title);
        model.addAttribute("subject", subject);
        return "subjects/show";
    }

}
