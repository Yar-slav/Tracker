package com.example.tracker.service;

import com.example.tracker.mapper.ProjectMapper;
import com.example.tracker.model.Project;
import com.example.tracker.model.dto.ProjectRequestDto;
import com.example.tracker.model.dto.ProjectResponseDto;
import com.example.tracker.repository.ProjectRepo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;

    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        Project project = projectMapper.toProject(projectRequestDto);
        project = projectRepo.save(project);
        return projectMapper.toProjectResponseDto(project);
    }

    public ProjectResponseDto getProject(Long id) {
        Project project = findProject(id);
        return projectMapper.toProjectResponseDto(project);
    }

    public Project findProject(Long projectId) {
        Optional<Project> projectOptional = projectRepo.findById(projectId);
        if(projectOptional.isPresent()) {
            return projectOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Project not found");
        }
    }

    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream()
                .map(projectMapper::toProjectResponseDto)
                .collect(Collectors.toList());
    }
}
