package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.User;
import com.codeup.stackknot.models.Whiteboard;
import com.codeup.stackknot.repositories.UserRepository;
import com.codeup.stackknot.repositories.WhiteboardRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class WhiteboardController {

    // DEPENDENCY INJECTION
    private UserRepository userDao;
    private WhiteboardRepository whiteboardDao;
    private String imageTag;

    public WhiteboardController(WhiteboardRepository whiteboardDao) {
        this.whiteboardDao = whiteboardDao;
    }

    //     SHOW WHITEBOARD SECTION
    @GetMapping("/whiteboard")
    public String getStartedPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", loggedInUser);
        }
        return "whiteboard/whiteboardStart";
    }

    public Long shuffleBoards(List<Whiteboard> boards) {
        boards = whiteboardDao.findAll();
        Collections.shuffle(boards);
        List<Long> getIds = new ArrayList<>();
        for(Whiteboard board : boards) {
            getIds.add(board.getId());
        }
        return getIds.get(0);

    }

    @PostMapping("/whiteboardStart")
    public String sendToRandomWhiteboard() {
        long id = shuffleBoards(whiteboardDao.findAll());
        return "redirect:whiteboard/" + id;
    }

    @GetMapping("/whiteboard/{id}")
    public String showEditor(@PathVariable long id, Model model) {
        model.addAttribute("whiteboard", whiteboardDao.getById(id));
        return "whiteboard/whiteboard";
    }

    @PostMapping("/whiteboard-next")
    public String sendToNextWhiteboard() {
        Long nextId = shuffleBoards(whiteboardDao.findAll());
        return "redirect:whiteboard/" + nextId;
    }

    //     ADMIN UPLOAD ABILITY
    @PostMapping("/whiteboard-upload")
    public String redirectToUpload(@ModelAttribute User user) {
        return "redirect:whiteboard/upload";
    }
    @GetMapping("/whiteboard/upload")
    public String uploadSolutionForm(Model model) {
        model.addAttribute("newBoard", new Whiteboard());
        return "whiteboard/upload";
    }

    @PostMapping("/whiteboard/upload")
    public String uploadSolution(@ModelAttribute Whiteboard whiteboard) {
        whiteboardDao.save(whiteboard);
        return "redirect:/whiteboard";
    }

}
