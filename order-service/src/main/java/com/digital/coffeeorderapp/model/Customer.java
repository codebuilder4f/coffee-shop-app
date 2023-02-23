package com.digital.coffeeorderapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private Long id;
    private String name;
    private String address;
    private String mobileNumber;
    private int score;
}
