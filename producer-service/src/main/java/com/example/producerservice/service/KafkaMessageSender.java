package com.example.producerservice.service;

import com.example.producerservice.dto.AbstractDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class KafkaMessageSender {
    private final KafkaTemplate<Long, AbstractDto> kafkaDtoTemplate;
    private final ObjectMapper objectMapper;

    public KafkaMessageSender(KafkaTemplate<Long, AbstractDto> kafkaDtoTemplate) {
        this.kafkaDtoTemplate = kafkaDtoTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void send() {
        AbstractDto dto = new AbstractDto();

        if (writeValueAsString(dto).isPresent()) {
            log.info("Sending => {}", writeValueAsString(dto).get());
            kafkaDtoTemplate.send("myTopic1", dto);
        }
    }

    private Optional<String> writeValueAsString(AbstractDto dto) {
        try {
            return Optional.of(objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            log.warn("Exception writing value to json: {}", e.getMessage());
        }
        return Optional.empty();
    }
}
