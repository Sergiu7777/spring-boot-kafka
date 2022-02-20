package com.example.consumerservice.service;

import com.example.consumerservice.dto.AbstractDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class KafkaMessageConsumer {
    private final ObjectMapper objectMapper;

    public KafkaMessageConsumer() {
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(id = "AbstractDto", topics = "myTopic1", groupId = "server.broadcast")
    public void listen(AbstractDto dto) {
        log.info("Received Message in group - groupId: {}", writeValueAsString(dto));
    }

    private String writeValueAsString(AbstractDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
