package com.javaexpress.acounts.clients;

import com.javaexpress.acounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CartFeignClient {

    @GetMapping("api/fetch")
    public CardsDto fetchCardDetails(@RequestParam String mobileNumber);
}
