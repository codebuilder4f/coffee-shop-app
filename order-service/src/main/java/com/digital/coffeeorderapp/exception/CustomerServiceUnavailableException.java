package com.digital.coffeeorderapp.exception;

public class CustomerServiceUnavailableException extends RuntimeException{

    public CustomerServiceUnavailableException(String message){
        super(message);
    }
}
