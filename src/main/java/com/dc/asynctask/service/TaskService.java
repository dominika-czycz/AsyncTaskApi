package com.dc.asynctask.service;

import com.dc.asynctask.dto.ResultDto;
import com.dc.asynctask.dto.TaskDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    /**
     * Creates and persists a task
     * @param taskDto with data to save
     * @return taskDto with id
     */
    TaskDto createTask(TaskDto taskDto);

    /**
     * Processes task with a given id
     * @param id of task
     */
    @Async
    void processTask(String id);

    /**
     * Returns all instances of the task.
     * @return all entities
     */
    List<TaskDto> getAllTasks();

    /**
     * Retrieves a task by its id.
     * Params:
     * @param taskId must not be null
     * @return a task
     */
    TaskDto getTask(String taskId);
    /**
     * Retrieves a result of a task by its id.
     * Params:
     * @param taskId must not be null
     * @return a result
     */
    ResultDto getResult(String taskId);

}
