package com.example.tracker.mapper;

import com.example.tracker.model.Project;
import com.example.tracker.model.dto.ProjectRequestDto;
import com.example.tracker.model.dto.ProjectResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toProject(ProjectRequestDto projectRequestDto);
    ProjectResponseDto toProjectResponseDto(Project project);
}
