package com.dc.asynctask.service;

import com.dc.asynctask.repository.TaskRepository;
import com.dc.asynctask.dto.ResultDto;
import com.dc.asynctask.dto.TaskDto;
import com.dc.asynctask.exception.NotFoundException;
import com.dc.asynctask.model.Result;
import com.dc.asynctask.model.Task;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Log4j2
public class AsyncTaskService implements TaskService {
    private final TaskRepository taskRepository;
    private final NumberFormat percentInstance = NumberFormat.getPercentInstance();

    public AsyncTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskDto.convertToTask();
        task.setStatus(percentInstance.format(NumberUtils.INTEGER_ZERO));
        Task savedTask = taskRepository.save(task);
        taskDto.setId(savedTask.getId());
        log.info("Task with id {} created", task.getId());
        return taskDto;
    }

    @Async
    @Override
    public void processTask(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("No task with id: " + taskId));
        log.info("Start of precessing the task with id {} ", taskId);
        task.setResult(findBestMatch(task));
        taskRepository.save(task);
        log.info("The task with id {} has been processed", task.getId());
    }

    @SneakyThrows(InterruptedException.class)
    private Result findBestMatch(Task task) {
        String input = task.getInput();
        String pattern = task.getPattern();
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

        Map<Integer, Result> results = new TreeMap<>();
        int chunks = input.length() - pattern.length() + NumberUtils.INTEGER_ONE;

        for (int i = 0; i < chunks; i++) {
            CharSequence toCheck = input.subSequence(i, pattern.length() + i);
            Integer distance = levenshteinDistance.apply(toCheck, pattern);

            if (distance.equals(NumberUtils.INTEGER_ZERO)) {
                task.setStatus(percentInstance.format(NumberUtils.INTEGER_ONE));
                taskRepository.save(task);
                return new Result(i, distance);
            }

            results.putIfAbsent(distance, new Result(i, distance));
            updateStatus(task, chunks, i);
            Thread.sleep(1000);
        }
        return results.entrySet().stream().findFirst().get().getValue();
    }

    private void updateStatus(Task task, int chunks, int i) {
        double status = (i + NumberUtils.DOUBLE_ONE) / chunks;
        task.setStatus(percentInstance.format(status));
        taskRepository.save(task);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskDto::getDto).toList();
    }

    @Override
    @Cacheable(value = "task", key = "#taskId", unless = "!#result.isDone()")
    public TaskDto getTask(String taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("No task with id: " + taskId));
        return TaskDto.getDto(task);
    }

    @Override
    @Cacheable(value = "result", key = "#taskId", unless = "#result.getPosition()==null")
    public ResultDto getResult(String taskId) {
        Result result = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException("No task with id: " + taskId))
                .getResult();
        return result != null ? new ResultDto(result.getPosition(), result.getTypos()) : new ResultDto();
    }
}
