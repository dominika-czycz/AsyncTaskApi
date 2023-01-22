package com.dc.asyncTaskApi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Task {
    @Id
    private String id;
    private String input;
    private String pattern;
    private Result result;

    private Double status;

}
