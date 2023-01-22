package com.dc.asynctask.service;

import com.dc.asynctask.dto.ResultDto;
import com.dc.asynctask.dto.TaskDto;
import com.dc.asynctask.model.Task;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
//    TODO add comments for doc
    TaskDto createTask(TaskDto taskDto);

    @Async
    void processTask(String id);

    List<Task> getAllTasks();

    TaskDto getTask(String taskId);

    ResultDto getResult(String taskId);

}
