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
    @GetMapping("/cards/create/{setId}")
    public String createCardForm(Model model, @PathVariable long setId) {
        Card card = new Card();
        Set set = setDao.getById(setId);
        card.setSet(set);
        model.addAttribute("newCard", card);
        return "cards/create";
    }

    @PostMapping("/cards/create")
    public String createCard(@ModelAttribute Card card) {
        cardDao.save(card);
        return "redirect:../sets/" + card.getSet().getId();
    }

    // EDIT CARDS
    @GetMapping("/cards/{id}/edit")
    public String editCardForm(@PathVariable long id, Model model) {
        Card card = cardDao.getById(id);
            model.addAttribute("card", card);
            return "cards/edit";
    }

    @PostMapping("/cards/{id}/edit")
    public String editCard(@ModelAttribute Card card, @PathVariable long id) {
        cardDao.save(card);
        return "redirect:../../sets/" + card.getSet().getId();
    }

    // DELETE CARD
    @GetMapping("/cards/{id}/delete")
    public String deleteForm(@PathVariable long id, Model model) {
        Card card = cardDao.getById(id);
            model.addAttribute("card", card);
            return "cards/delete";
    }

    @PostMapping("cards/{id}/delete")
    public String deleteCard(@PathVariable long id) {
        Card card = cardDao.getById(id);
        cardDao.deleteById(id);
        return  "redirect:../../sets/" + card.getSet().getId();
    }


}
