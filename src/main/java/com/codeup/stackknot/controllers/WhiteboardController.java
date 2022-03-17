package com.codeup.stackknot.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.codeup.stackknot.models.Whiteboard;
import com.codeup.stackknot.repositories.UserRepository;
import com.codeup.stackknot.repositories.WhiteboardRepository;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
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

    //    public Test generateTest(@PathVariable long setId) {
//        List<Card>questions = cardDao.findAllBySetId(setId);
//        List<String>answers = new List<String>() {
//        };
//        List<TestQuestion>testQuestions = new ;
//
//        for (Card question : questions) {
//            answers.add(question.getAnswer());
//        }
//
//        for (Card question : questions ) {
//
//            TestQuestion testQuestion = new TestQuestion();
//
//            testQuestion.setQuestion(question.getQuestion());
//            testQuestion.setCorrectAnswer(question.getAnswer());
//
//            do {
//                Collections.shuffle(answers);
//                testQuestion.setWrongAnswer1(answers.get(1));
//                testQuestion.setWrongAnswer2(answers.get(2));
//                testQuestion.setWrongAnswer3(answers.get(3));
//            } while ( testQuestion.getCorrectAnswer().equals(testQuestion.getWrongAnswer1()) || testQuestion.getCorrectAnswer().equals( testQuestion.getWrongAnswer2()) || testQuestion.getCorrectAnswer().equals(testQuestion.getWrongAnswer3())
//            || testQuestion.getWrongAnswer1().equals(testQuestion.getWrongAnswer2()) || testQuestion.getWrongAnswer2().equals(testQuestion.getWrongAnswer3()) || testQuestion.getWrongAnswer1().equals(testQuestion.getWrongAnswer3()));
//
//
//            testQuestions.add(testQuestion);
//        }
//
//        return new Test(testQuestions);
//    }
    public Whiteboard shuffleBoards(@PathVariable Long id) {
        List<Whiteboard> allBoardIds = whiteboardDao.findAllById(id);
        List<Long> ids = new ArrayList<>();
        List<Long> randomId = new ArrayList<>();
        for (Whiteboard board : allBoardIds) {
            ids.add(board.getId());
        }
        Collections.shuffle(ids);
        Whiteboard singleWhiteboard = new Whiteboard();
        singleWhiteboard.setId(ids.get(0));

        return singleWhiteboard;

    }

    @GetMapping("/whiteboard/{id}")
    public String showEditor(@PathVariable long id, Model model) {
        model.addAttribute("whiteboard", shuffleBoards(id));
        return "whiteboard/whiteboard";
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
