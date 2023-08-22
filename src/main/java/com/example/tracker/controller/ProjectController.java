package com.example.tracker.controller;

import com.example.tracker.model.dto.ProjectRequestDto;
import com.example.tracker.model.dto.ProjectResponseDto;
import com.example.tracker.model.dto.TaskResponseDto;
import com.example.tracker.service.ProjectService;
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
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public String addProject(@ModelAttribute ProjectRequestDto projectRequestDto) {
        projectService.addProject(projectRequestDto);
        return "redirect:/projects"; // Повернення на сторінку зі списком проектів після додавання
    }

    @GetMapping
    public String projects(Model model) {
        List<ProjectResponseDto> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("projectRequestDto", new ProjectRequestDto());
        return "projects"; // Відправлення на сторінку projects.html
    }


    @GetMapping("/{id}")
    public String projectDetails(@PathVariable Long id, Model model) {
        ProjectResponseDto project = projectService.getProject(id);
        List<TaskResponseDto> tasks = taskService.getTasksByProjectId(id);
        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);
        return "project-details"; // Відправлення на сторінку project-details.html
    }
}

