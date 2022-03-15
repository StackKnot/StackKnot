package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Card;
import com.codeup.stackknot.models.Test;
import com.codeup.stackknot.models.TestQuestion;
import com.codeup.stackknot.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
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
        List<String>answers = new ArrayList<>();
        List<TestQuestion>testQuestions = new ArrayList<>();

        for (Card question : questions) {
            answers.add(question.getAnswer());
        }

        for (Card question : questions ) {

            TestQuestion testQuestion = new TestQuestion();

            testQuestion.setQuestion(question.getQuestion());
            testQuestion.setAnswer1(question.getAnswer());

            do {
                Collections.shuffle(answers);
                testQuestion.setAnswer2(answers.get(1));
                testQuestion.setAnswer3(answers.get(2));
                testQuestion.setAnswer4(answers.get(3));
            } while ( testQuestion.getAnswer1().equals(testQuestion.getAnswer2()) || testQuestion.getAnswer1().equals( testQuestion.getAnswer3()) || testQuestion.getAnswer1().equals(testQuestion.getAnswer4())
            || testQuestion.getAnswer2().equals(testQuestion.getAnswer3()) || testQuestion.getAnswer3().equals(testQuestion.getAnswer4()) || testQuestion.getAnswer2().equals(testQuestion.getAnswer4()));

            List<String> questionAnswers = new ArrayList<>();
            questionAnswers.add(testQuestion.getAnswer1());
            questionAnswers.add(testQuestion.getAnswer2());
            questionAnswers.add(testQuestion.getAnswer3());
            questionAnswers.add(testQuestion.getAnswer4());

            Collections.shuffle(questionAnswers);
            testQuestion.setAnswer1(questionAnswers.get(0));
            testQuestion.setAnswer2(questionAnswers.get(1));
            testQuestion.setAnswer3(questionAnswers.get(2));
            testQuestion.setAnswer4(questionAnswers.get(3));


            testQuestions.add(testQuestion);
        }
        Collections.shuffle(testQuestions);
        return new Test(testQuestions, setId);
    }

    @GetMapping("/tests/{setId}")
    public String showTest(@PathVariable long setId, Model model) {
        model.addAttribute("test", generateTest(setId));
        return "tests/show";
    }




}
