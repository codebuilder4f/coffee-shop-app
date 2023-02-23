package com.digital.coffeeorderapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@Slf4j
public class QueueService {
    /**
     * In a real-world implementation, might use a more sophisticated queue implementation,
     * such as Apache Kafka or RabbitMQ, to handle the orders more efficiently and reliably.
     */
    private final Map<Long, ConcurrentLinkedQueue<String>> coffeeShopQueues = new ConcurrentHashMap<>();

    public void addToQueue(Long coffeeShopId, Long orderId) {
        coffeeShopQueues.computeIfAbsent(coffeeShopId, k -> new ConcurrentLinkedQueue<>());
        coffeeShopQueues.get(coffeeShopId).offer(orderId.toString());
        coffeeShopQueues.forEach((key, value) -> log.info("Queue details : " + key + " " + value));
    }
}
