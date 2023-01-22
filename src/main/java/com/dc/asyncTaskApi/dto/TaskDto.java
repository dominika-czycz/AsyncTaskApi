package com.dc.asyncTaskApi.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String id;
    private String input;
    private String pattern;
    private ResultDto resultDto;

}
