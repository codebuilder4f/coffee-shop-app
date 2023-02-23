package com.digital.coffeeorderapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.digital.coffeeorderapp.model.Customer;
import com.digital.coffeeorderapp.model.Order;
import com.digital.coffeeorderapp.proxies.CustomerServiceProxy;
import com.digital.coffeeorderapp.repository.OrderRepository;
import com.digital.coffeeorderapp.dto.OrderItemDto;
import com.digital.coffeeorderapp.dto.request.OrderRequest;
import com.digital.coffeeorderapp.dto.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CoffeeOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerServiceProxy customerServiceProxy;

    @Mock
    private QueueService queueService;

    @InjectMocks
    private CoffeeOrderService orderService;

    @Test
    public void placeOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomerId(1L);
        orderRequest.setCoffeeShopId(1L);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setMenuItemId(1L);
        orderItemDto.setQuantity(2);
        orderRequest.setOrderItemDto(Collections.singletonList(orderItemDto));
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        when(customerServiceProxy.findById(1L)).thenReturn(customer);
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        assertEquals("success", orderResponse.getStatus());
        assertEquals("Order placed successfully", orderResponse.getMessage());

    }
}