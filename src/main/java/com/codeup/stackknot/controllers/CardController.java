package com.codeup.stackknot.controllers;

import com.codeup.stackknot.models.Card;
import com.codeup.stackknot.models.Set;
import com.codeup.stackknot.models.User;
import com.codeup.stackknot.repositories.CardRepository;
import com.codeup.stackknot.repositories.SetRepository;
import com.codeup.stackknot.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CardController {
    //Dependency Injection
    private CardRepository cardDao;
    private SetRepository setDao;
    private UserRepository userDao;

    public CardController(CardRepository cardDao, SetRepository setDao, UserRepository userDao) {
        this.cardDao = cardDao;
        this.setDao = setDao;
        this.userDao = userDao;
    }

    // CREATE CARD
    @GetMapping("/cards/create")
    public String createCardForm(Model model) {
        model.addAttribute("newCard", new Card());
        return "cards/create";
    }

    @PostMapping("/cards/create")
    public String createCard(@ModelAttribute Card card) {
        card.setSet(setDao.getById(2L));
        cardDao.save(card);
        return "redirect:../sets/" + 2L;
    }

    // SHOW ALL CARDS
    @GetMapping("/cards/{id}")
    public String allCards(@PathVariable long id, Model model) {
        model.addAttribute("allCards", setDao.getById(id).getCards());
        return "cards/index";
    }

    // EDIT CARDS
    @GetMapping("/cards/{id}/edit")
    public String editCardForm(@PathVariable long id, Model model) {
        Card card = cardDao.getById(id);
        Set correctSet = setDao.getById(2L);
        if (card.getSet().getId() == correctSet.getId()) {
            model.addAttribute("card", card);
            return "cards/edit";
        } else {
            return "redirect:/sets";
        }
    }

    @PostMapping("/cards/{id}/edit")
    public String editCard(@ModelAttribute Card card, @PathVariable long id) {
        Set correctSet = setDao.getById(2L);
        card.setSet(correctSet);
        cardDao.save(card);
        return "redirect:/sets";
    }

    // DELETE CARD
    @GetMapping("/cards/{id}/delete")
    public String deleteForm(@PathVariable long id, Model model) {
        Card card = cardDao.getById(id);
        Set correctSet = setDao.getById(2L);
        if (card.getSet().getId() == correctSet.getId()) {
            model.addAttribute("card", card);
            return "cards/delete";
        } else {
            return "redirect:/sets";
        }
    }

    @PostMapping("cards/{id}/delete")
    public String deleteCard(@ModelAttribute Card card, @PathVariable long id) {
        cardDao.deleteById(id);
        return "redirect:/sets";
    }
}
