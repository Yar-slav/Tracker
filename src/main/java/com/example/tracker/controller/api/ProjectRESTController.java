package com.example.tracker.controller.api;

import com.example.tracker.model.dto.ProjectRequestDto;
import com.example.tracker.model.dto.ProjectResponseDto;
import com.example.tracker.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracker/projects")
@RequiredArgsConstructor
public class ProjectRESTController {

    private final ProjectService projectService;

    @PostMapping
    public ProjectResponseDto addProject(@RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.addProject(projectRequestDto);
    }

    @GetMapping("/{id}")
    public ProjectResponseDto getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {
        return projectService.getAllProjects();
    }
}
