package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Card;
import com.codeup.stackknot.models.Test;
import com.codeup.stackknot.models.TestQuestion;
import com.codeup.stackknot.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
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


    public Test generateTest(@PathVariable long setId) {
        List<Card>questions = cardDao.findAllBySetId(setId);
        List<String>answers;
        List<TestQuestion>testQuestions;

        for (Card question : questions) {
            answers.add(question.getAnswer());
        }

        for (Card question : questions ) {

            TestQuestion testQuestion = new TestQuestion();

            testQuestion.setQuestion(question.getQuestion());
            testQuestion.setCorrectAnswer(question.getAnswer());

            do {
                Collections.shuffle(answers);
                testQuestion.setWrongAnswer1(answers.get(1));
                testQuestion.setWrongAnswer2(answers.get(2));
                testQuestion.setWrongAnswer3(answers.get(3));
            } while ( testQuestion.getCorrectAnswer().equals(testQuestion.getWrongAnswer1()) || testQuestion.getCorrectAnswer().equals( testQuestion.getWrongAnswer2()) || testQuestion.getCorrectAnswer().equals(testQuestion.getWrongAnswer3())
            || testQuestion.getWrongAnswer1().equals(testQuestion.getWrongAnswer2()) || testQuestion.getWrongAnswer2().equals(testQuestion.getWrongAnswer3()) || testQuestion.getWrongAnswer1().equals(testQuestion.getWrongAnswer3()));


            testQuestions.add(testQuestion);
        }

        return new Test(testQuestions);
    }


    @GetMapping("/test/{setId}")
    public String showTest(@PathVariable long setId, Model model) {
        model.addAttribute("test", generateTest(setId));
        return "tests/show";
    }




}
