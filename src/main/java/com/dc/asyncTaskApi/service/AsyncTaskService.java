package com.dc.asyncTaskApi.service;

import com.dc.asyncTaskApi.Repository.TaskRepository;
import com.dc.asyncTaskApi.dto.ResultDto;
import com.dc.asyncTaskApi.dto.TaskDto;
import com.dc.asyncTaskApi.exception.BadRequestException;
import com.dc.asyncTaskApi.model.Result;
import com.dc.asyncTaskApi.model.Task;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AsyncTaskService implements TaskService {
    private final TaskRepository taskRepository;

    public AsyncTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public String createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setPattern(taskDto.getPattern());
        task.setInput(taskDto.getInput());
        task.setStatus(NumberUtils.DOUBLE_ZERO);

        Task savedTask = taskRepository.save(task);
        return savedTask.getId();
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Double getStatus(String taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new BadRequestException("No task with id: " + taskId))
                .getStatus();
    }

    @Override
    public ResultDto getResult(String taskId) {
        Result result = taskRepository.findById(taskId)
                .orElseThrow(() -> new BadRequestException("No task with id: " + taskId))
                .getResult();
        return new ResultDto(result.getPosition(), result.getTypos());
    }
}
