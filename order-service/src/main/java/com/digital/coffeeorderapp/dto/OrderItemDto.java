package com.digital.coffeeorderapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long menuItemId;
    private int quantity;
}
