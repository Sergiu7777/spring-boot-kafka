package com.example.producerservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class AbstractDto implements Serializable {
    String value;

    public AbstractDto() {
        this.value = "Dto " + (LocalTime.now().toNanoOfDay() / 1000000);
    }
}
