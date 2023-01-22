package com.dc.asyncTaskApi.api.v0;

import com.dc.asyncTaskApi.dto.ResultDto;
import com.dc.asyncTaskApi.dto.TaskDto;
import com.dc.asyncTaskApi.model.Task;
import com.dc.asyncTaskApi.service.TaskService;
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
    public String createTask(@RequestBody TaskDto task) {
        return taskService.createTask(task);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{id}/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDto getTaskResult(@PathVariable String id) {
        return taskService.getResult(id);
    }

    @GetMapping(value = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public Double getTaskStatus(@PathVariable String id) {
        return taskService.getStatus(id);
    }

}
