package com.dc.asyncTaskApi.service;

import com.dc.asyncTaskApi.dto.ResultDto;
import com.dc.asyncTaskApi.dto.TaskDto;
import com.dc.asyncTaskApi.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    String createTask(TaskDto taskDto);

    List<Task> getAllTasks();

    Double getStatus(String taskId);

    ResultDto getResult(String taskId);

}
