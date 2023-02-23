package com.digital.coffeeorderapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

    private String orderId;
    private String status;
    private String message;

}
