package com.javaexpress.carts.service;

import com.javaexpress.carts.dto.CardsDto;

public interface ICardService {

    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto crCardsDto);

    boolean deleteCard(String mobileNumber);
}
