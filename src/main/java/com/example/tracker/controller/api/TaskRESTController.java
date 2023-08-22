package com.example.tracker.controller.api;

import com.example.tracker.model.dto.TaskRequestDto;
import com.example.tracker.model.dto.TaskResponseDto;
import com.example.tracker.model.dto.TaskTimeDto;
import com.example.tracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker/task")
@RequiredArgsConstructor
public class TaskRESTController {

    private final TaskService taskService;


    @PostMapping
    public TaskResponseDto createTask(
            @RequestBody TaskRequestDto taskRequestDto) {
        return taskService.createTask(taskRequestDto);
    }

    @GetMapping("/{id}")
    public TaskResponseDto getProject(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PutMapping()
    public TaskResponseDto setTime(@RequestBody TaskTimeDto taskTimeDto) {
        return taskService.setTime(taskTimeDto);
    }
}
