package com.example.tracker.mapper;

import com.example.tracker.model.Task;
import com.example.tracker.model.dto.TaskRequestDto;
import com.example.tracker.model.dto.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toTask(TaskRequestDto taskRequestDto);

    @Mapping(target = "projectId", source = "project.id")
    TaskResponseDto toTaskResponseDto(Task task);
}
