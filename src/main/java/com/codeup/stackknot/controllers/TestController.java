package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.*;
import com.codeup.stackknot.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private UserSetProgRepository userSetProgDao;

    public TestController(SetRepository setDao, CardRepository cardDao, SubjectRepository subjectDao, UserRepository userDao, ProgressionRepository progressionDao, UserSetProgRepository userSetProgDao) {
        this.setDao = setDao;
        this.cardDao = cardDao;
        this.subjectDao = subjectDao;
        this.userDao = userDao;
        this.progressionDao = progressionDao;
        this.userSetProgDao = userSetProgDao;
    }

    public Test generateTest(@PathVariable long setId) {
        List<Card> questions = cardDao.findAllBySetId(setId);
        List<String> answers = new ArrayList<>();
        List<TestQuestion> testQuestions = new ArrayList<>();

        for (Card question : questions) {
            answers.add(question.getAnswer());
        }

        for (Card question : questions) {

            TestQuestion testQuestion = new TestQuestion();

            testQuestion.setQuestion(question.getQuestion());
            testQuestion.setAnswer1(question.getAnswer());
            testQuestion.setCorrectAnswer(question.getAnswer());

            do {
                Collections.shuffle(answers);
                testQuestion.setAnswer2(answers.get(1));
                testQuestion.setAnswer3(answers.get(2));
                testQuestion.setAnswer4(answers.get(3));
            } while (testQuestion.getAnswer1().equals(testQuestion.getAnswer2()) || testQuestion.getAnswer1().equals(testQuestion.getAnswer3()) || testQuestion.getAnswer1().equals(testQuestion.getAnswer4())
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

    public Test gradeTest(Test test) {

        double totalQuestions = 0;
        double totalCorrect = 0;

        for (TestQuestion question : test.getTestQuestions()) {

            String correctAnswer = cardDao.getByQuestionAndSetId(question.getQuestion(), test.getSetId()).getAnswer();
            question.setCorrectAnswer(correctAnswer);

            if (question.getUserChoice().equals(question.getCorrectAnswer())) {
                question.setCorrect(true);
                totalCorrect++;
            } else {
                question.setCorrect(false);
            }
            totalQuestions++;
        }
        double userGradePercentage = (totalCorrect / totalQuestions) * 100;
        test.setGrade(userGradePercentage);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserSetProg existingProg = userSetProgDao.findByUserAndAndSet(loggedInUser, setDao.getById(test.getSetId()));
        if (existingProg == null) {
            UserSetProg setProg = new UserSetProg();
            setProg.setUser(loggedInUser);
            setProg.setSet(setDao.getById(test.getSetId()));
            if (userGradePercentage > 90F) {
                setProg.setProgression(progressionDao.getByStatus("Mastered"));
            } else if (userGradePercentage > 70) {
                setProg.setProgression(progressionDao.getByStatus("Satisfactory"));
            } else {
                setProg.setProgression(progressionDao.getByStatus("Needs Work"));
            }
            userSetProgDao.save(setProg);
        } else {
            if (!existingProg.getProgression().getStatus().equals("Mastered")) {
                if (userGradePercentage > 90F) {
                    existingProg.setProgression(progressionDao.getByStatus("Mastered"));
                } else if (userGradePercentage > 70) {
                    existingProg.setProgression(progressionDao.getByStatus("Satisfactory"));
                } else {
                    existingProg.setProgression(progressionDao.getByStatus("Needs Work"));
                }
                userSetProgDao.save(existingProg);
            }

        }
        return test;
    }

    @GetMapping("/tests/{setId}")
    public String showTest(@PathVariable long setId, Model model) {
        model.addAttribute("test", generateTest(setId));
        return "tests/show";
    }

    @PostMapping("/tests/{setId}")
    public String gradeTest(@ModelAttribute Test test, Model model) {
        model.addAttribute("test", test);
        return "redirect:tests/grade";
    }

    @GetMapping("/tests/{setId}/grade")
    public String showgGradedTest(@ModelAttribute Test test, Model model) {
        model.addAttribute("test", gradeTest(test));
        return "tests/grade";
    }


}
