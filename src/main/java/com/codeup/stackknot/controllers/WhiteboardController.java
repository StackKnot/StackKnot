package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Whiteboard;
import com.codeup.stackknot.repositories.UserRepository;
import com.codeup.stackknot.repositories.WhiteboardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
//@RequestMapping(value="/whiteboard")
public class WhiteboardController {

    @Value("${COM_CLOUDINARY_CLOUD_NAME}")
    String mCloudName;


    @Value("${COM_CLOUDINARY_API_KEY}")
    String mApiKey;


    @Value("${COM_CLOUDINARY_API_SECRET}")
    String mApiSecret;


    // DEPENDENCY INJECTION
    private UserRepository userDao;
    private WhiteboardRepository whiteboardDao;
    private String imageTag;

    public WhiteboardController(WhiteboardRepository whiteboardDao) {
        this.whiteboardDao = whiteboardDao;
    }

    //     SHOW WHITEBOARD SECTION
    @GetMapping("/whiteboard")
    public String getStartedPage() {
        whiteboardDao.findAll();
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
