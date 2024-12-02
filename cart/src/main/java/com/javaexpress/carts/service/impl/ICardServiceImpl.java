package com.javaexpress.carts.service.impl;

import com.javaexpress.carts.dto.CardsDto;
import com.javaexpress.carts.entity.Cards;
import com.javaexpress.carts.exception.CardAlreadyExistException;
import com.javaexpress.carts.repository.CardsRepository;
import com.javaexpress.carts.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ICardServiceImpl implements ICardService {
    private CardsRepository cardsRepository;
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistException("Card Already exists");
        }

        createNewCard(mobileNumber);
    }

    private void createNewCard(String mobileNumber) {
        Cards cards = new Cards();
        long randomAcNo = 1000000L + new Random().nextInt(1000000);
        cards.setMobileNumber(mobileNumber);
        cards.setCardNumber(String.valueOf(randomAcNo));
        cards.setCardType("CREDIT_CARD");
        cards.setTotalLimit(100000);
        cards.setAmountUsed(0);
        cards.setAvailableAmount(100000);
        cardsRepository.save(cards);
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards dbCardInfo = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CardAlreadyExistException("Card doesn't exist with given mobileNo: " + mobileNumber));
        CardsDto cardsDto = new CardsDto();
        BeanUtils.copyProperties(dbCardInfo, cardsDto);
        return cardsDto;
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards dbCardInfo = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
                .orElseThrow(() -> new CardAlreadyExistException("Card Number not exist with cardNo: " + cardsDto.getCardNumber()));
        BeanUtils.copyProperties(cardsDto, dbCardInfo);
        cardsRepository.save(dbCardInfo);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards dbCardInfo = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CardAlreadyExistException("mobileNo is not associated with any card: " + mobileNumber));
        cardsRepository.deleteById(dbCardInfo.getCardId());
        return true;
    }
}
