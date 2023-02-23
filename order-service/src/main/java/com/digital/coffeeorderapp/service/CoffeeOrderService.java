package com.digital.coffeeorderapp.service;

import com.digital.coffeeorderapp.exception.CustomerNotFoundException;
import com.digital.coffeeorderapp.model.Customer;
import com.digital.coffeeorderapp.model.Order;
import com.digital.coffeeorderapp.model.OrderItem;
import com.digital.coffeeorderapp.proxies.CustomerServiceProxy;
import com.digital.coffeeorderapp.repository.OrderRepository;
import com.digital.coffeeorderapp.dto.OrderItemDto;
import com.digital.coffeeorderapp.dto.request.OrderRequest;
import com.digital.coffeeorderapp.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CoffeeOrderService {
    private final QueueService queueService;
    private final OrderRepository orderRepository;
    private final CustomerServiceProxy customerServiceProxy;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Long customerId = orderRequest.getCustomerId();
        Customer customer = customerServiceProxy.findById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + customerId + " not found");
        }
        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setCoffeeShopId(orderRequest.getCoffeeShopId());

        /**
         * The other microservice should manage the menu item.
         * Before adding data to the order table, we must verify that all requested items
         * are available in that service and, in the event that they are not, we must notify
         * the client.
         */
        List<OrderItem> orderItemList = orderRequest.getOrderItemDto()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderItems(orderItemList);

        log.info("Going to save order with order number " + order.getOrderNumber());
        orderRepository.save(order);

        customerServiceProxy.updateCustomerScore(customerId);
        addToQueue(orderRequest.getCoffeeShopId(), order.getId());
        return new OrderResponse(order.getOrderNumber(), "success", "Order placed successfully");
    }

    private void addToQueue(Long coffeeShopId, Long orderId) {
        queueService.addToQueue(coffeeShopId, orderId);
    }

    private OrderItem mapToDto(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItemId(orderItemDto.getMenuItemId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }
}
