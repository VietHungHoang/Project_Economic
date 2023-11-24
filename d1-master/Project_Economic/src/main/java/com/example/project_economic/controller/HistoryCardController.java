package com.example.project_economic.controller;

import com.example.project_economic.entity.CartItemEntity;
import com.example.project_economic.response.CartItemResponse;
import com.example.project_economic.service.CartItemService;
import com.example.project_economic.service.EmailSenderService;
import com.example.project_economic.service.HistoryCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/history")
public class HistoryCardController {
    private final HistoryCardService historyCardService;
    private final CartItemService cartItemService;
    private final EmailSenderService emailSenderService;

    public HistoryCardController(HistoryCardService historyCardService, CartItemService cartItemService, EmailSenderService emailSenderService) {
        this.historyCardService = historyCardService;
        this.cartItemService = cartItemService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/add/{userId}")
    public String addProductToHistoryCard(@PathVariable Long userId){
        List<CartItemResponse> cartItemResponses=this.cartItemService.listCartItem(userId);
        try{
            List<Long> ids=cartItemResponses.stream().map((cart)->{
                return cart.getProductResponse().getId();
            }).collect(Collectors.toList());
            this.historyCardService.addProductToHistoryCard(userId);
            this.emailSenderService.sendEmail(userId);
            this.cartItemService.deleteAllCartByUserId(userId);
            return "add success";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "add fail";
        }
    }
}
