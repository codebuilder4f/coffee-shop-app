package com.digital.coffeeorderapp.controller;

import com.digital.coffeeorderapp.CoffeeOrderAppApplication;
import com.digital.coffeeorderapp.dto.response.OrderResponse;
import com.digital.coffeeorderapp.service.CoffeeOrderService;
import com.digital.coffeeorderapp.dto.request.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/" + CoffeeOrderAppApplication.API_V + "/orders")
public class CoffeeOrderController {

    @Autowired
    private CoffeeOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
