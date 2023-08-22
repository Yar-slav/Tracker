package com.example.tracker.controller;

import com.example.tracker.model.dto.TaskRequestDto;
import com.example.tracker.model.dto.TaskResponseDto;
import com.example.tracker.model.dto.TaskTimeDto;
import com.example.tracker.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public String addTaskToProject(@ModelAttribute TaskRequestDto taskRequestDto) {
        taskService.createTask(taskRequestDto);
        return "redirect:/projects/"+ taskRequestDto.getProjectId(); // Повернення на сторінку project-details.html
    }

    @GetMapping
    public String tasks (@PathVariable Long projectId, Model model) {
        List<TaskResponseDto> tasks = taskService.getTasksByProjectId(projectId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskRequestDto", new TaskRequestDto());
        return "project-details"; // Відправлення на сторінку projects.html
    }

    @GetMapping("/{id}")
    public String getTaskDetails(@PathVariable Long id, Model model) {
        TaskResponseDto task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "task-details"; // Повернення на сторінку task-details.html
    }

    @PostMapping("/time")
    public String setTime(@ModelAttribute TaskTimeDto taskTimeDto) {
        taskService.setTime(taskTimeDto);
        return "redirect:/tasks/" + taskTimeDto.getId(); // Повернення на сторінку task-details.html
    }

    @PostMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "project-details";
    }
}
