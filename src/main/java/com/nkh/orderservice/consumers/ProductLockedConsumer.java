package com.nkh.orderservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nkh.orderservice.entity.Order;
import com.nkh.orderservice.events.ProductLockedEvent;
import com.nkh.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductLockedConsumer {
    private final ObjectMapper objectMapper;
    private final OrderRepo orderRepo;

    @KafkaListener(topics = "product_locked")
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2.0),
            exclude = {NullPointerException.class, IllegalArgumentException.class}
    )//k8s
    public void handleProductLockedEvent(String productString) throws JsonProcessingException {
        ProductLockedEvent productLockedEvent = objectMapper.readValue(productString, ProductLockedEvent.class);
        log.info("Receive product message: {}", productLockedEvent);
        Order order = orderRepo.findById(productLockedEvent.getOrderId())
                .orElseThrow(() -> new RuntimeException("KHong tim thay order"));
        order.setStatus("PREPARE");
        orderRepo.save(order);
        log.info("Change status order to PREPARE");
    }
}
