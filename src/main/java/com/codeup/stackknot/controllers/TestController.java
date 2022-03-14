package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Card;
import com.codeup.stackknot.models.TestQuestion;
import com.codeup.stackknot.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TestController {

    private SetRepository setDao;
    private CardRepository cardDao;
    private SubjectRepository subjectDao;
    private UserRepository userDao;
    private ProgressionRepository progressionDao;

    public TestController(SetRepository setDao, CardRepository cardDao, SubjectRepository subjectDao, UserRepository userDao, ProgressionRepository progressionDao) {
        this.setDao = setDao;
        this.cardDao = cardDao;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.progressionDao = progressionDao;
    }

//    @GetMapping("/tests/{setId}")
//    public String generateTest(@PathVariable long setId) {
//        List<Card>questions = cardDao.findAllBySetId(setId);
//        List<String>answers;
//        List<TestQuestion>testQuestions;
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
//
//        }
//
//
//    }





}
