package com.example.project_economic.controller;

import com.example.project_economic.entity.CartItemEntity;
import com.example.project_economic.response.CartItemResponse;
import com.example.project_economic.service.CartItemService;
import com.example.project_economic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/carts")
public class ShoppingCartController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserService userService;
    @GetMapping("/{userId}")
    public String showShoppingCart(Model model,
            @PathVariable Long userId
            , @AuthenticationPrincipal Authentication authentication){
        List<CartItemResponse>cartItemEntities=this.cartItemService.listCartItem(userId);
        model.addAttribute("cartItems",cartItemEntities);
        return "home/cart";
    }

}
