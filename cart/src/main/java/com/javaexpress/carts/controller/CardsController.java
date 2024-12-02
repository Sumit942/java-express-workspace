package com.javaexpress.carts.controller;

import com.javaexpress.carts.dto.CardsDto;
import com.javaexpress.carts.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CardsController {

    @Autowired
    private ICardService iCardService;

    @PostMapping("create")
    public String createCard(@RequestParam String mobileNumber) {
        iCardService.createCard(mobileNumber);
        return "Card Created Successfully!!";
    }

    @GetMapping("fetch")
    public CardsDto fetchCardDetails(@RequestParam String mobileNumber) {
        return iCardService.fetchCard(mobileNumber);
    }

    @PutMapping("update")
    public Boolean updateCardDetails(@RequestBody CardsDto cardsDto) {
        return iCardService.updateCard(cardsDto);
    }

    @DeleteMapping("delete")
    public Boolean deleteCardDetails(@RequestParam String mobileNumber) {
        return iCardService.deleteCard(mobileNumber);
    }
}
