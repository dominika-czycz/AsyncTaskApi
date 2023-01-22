package com.dc.asynctask.api.v0;

import com.dc.asynctask.dto.ResultDto;
import com.dc.asynctask.dto.TaskDto;
import com.dc.asynctask.model.Task;
import com.dc.asynctask.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v0/async/tasks")
public class AsyncTaskController {
    private final TaskService taskService;

    public AsyncTaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskDto createTask(@RequestBody TaskDto task) {
        TaskDto createdTask = taskService.createTask(task);
        taskService.processTask(createdTask.getId());
        return createdTask;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{id}/result")
    public ResultDto getTaskResult(@PathVariable String id) {
        return taskService.getResult(id);
    }

    @GetMapping(value = "/{id}/status")
    public TaskDto getTaskWithStatus(@PathVariable String id) {
        return taskService.getTask(id);
    }

}
