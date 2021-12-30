package com.msa.usermanagement.kafka;


import com.msa.usermanagement.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTest {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/kafka/{message}")
    public String sendKafka(@PathVariable String message){
        producerService.sendMessage(message);
        return message;
    }
}
