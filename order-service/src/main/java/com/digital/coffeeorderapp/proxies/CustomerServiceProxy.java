package com.digital.coffeeorderapp.proxies;

import com.digital.coffeeorderapp.exception.CustomerServiceUnavailableException;
import com.digital.coffeeorderapp.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceProxy {

    private final WebClient.Builder webClientBuilder;

    @Value("${customer-service.url}")
    private String url;

    @CircuitBreaker(name = "customer", fallbackMethod = "getCustomerFallback")
    public Customer findById(Long customerId) {
        log.info("Find customer by id");
        return webClientBuilder.build().get()
                .uri(url, customerId)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
    }

    private Customer getCustomerFallback(String id) {
        throw new CustomerServiceUnavailableException("Oops! Something went wrong, " +
                "please order after some time");
    }

    public void updateCustomerScore(Long customerId) {
        log.info("update customer score");
        webClientBuilder.build().patch()
                .uri(url, customerId)//TODO : KEEP THIS URL SOMEWHERE
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
