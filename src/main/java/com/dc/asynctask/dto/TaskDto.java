package com.dc.asynctask.dto;

import com.dc.asynctask.model.Task;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



@Data
public class TaskDto {
    private String id;
    @NotEmpty
    private String input;
    @NotEmpty
    private String pattern;
    private String status;

    public static TaskDto getDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setInput(task.getInput());
        taskDto.setPattern(task.getPattern());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    public Task convertToTask() {
        Task task = new Task();
        task.setPattern(pattern);
        task.setInput(input);
        return task;
    }

}
