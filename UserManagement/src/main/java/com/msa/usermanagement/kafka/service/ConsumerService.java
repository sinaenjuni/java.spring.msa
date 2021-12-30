package com.msa.usermanagement.kafka.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {
    @KafkaListener(topics = "user", groupId = "test")
    public void consume(String message) throws IOException{
        System.out.println("received:" + message);
    }
}
