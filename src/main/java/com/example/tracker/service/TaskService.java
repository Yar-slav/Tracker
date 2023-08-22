package com.example.tracker.service;

import com.example.tracker.mapper.TaskMapper;
import com.example.tracker.model.Project;
import com.example.tracker.model.Task;
import com.example.tracker.model.dto.TaskRequestDto;
import com.example.tracker.model.dto.TaskResponseDto;
import com.example.tracker.model.dto.TaskTimeDto;
import com.example.tracker.repository.TaskRepo;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final ProjectService projectService;


    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = taskMapper.toTask(taskRequestDto);
        Project project = projectService.findProject(taskRequestDto.getProjectId());
        task.setProject(project);
        task = taskRepo.save(task);
        return taskMapper.toTaskResponseDto(task);
    }


    public TaskResponseDto getTask(Long id) {
        Task task = findTaskById(id);
        return taskMapper.toTaskResponseDto(task);
    }

    public TaskResponseDto setTime(TaskTimeDto taskTimeDto) {
        Task task = findTaskById(taskTimeDto.getId());
        LocalTime timeSpent = getTimeSpentAsLocalTime(taskTimeDto.getTimeSpent());

        LocalTime currentTime = task.getTimeSpent();
        task.setTimeSpent(addTime(timeSpent, currentTime));
        task = taskRepo.save(task);
        return taskMapper.toTaskResponseDto(task);
    }

    private static LocalTime addTime(LocalTime timeSpent, LocalTime currentTime) {
        return currentTime.plusHours(timeSpent.getHour()).plusMinutes(timeSpent.getMinute());
    }

    private LocalTime getTimeSpentAsLocalTime(String timeSpent) {
        if (timeSpent == null || !timeSpent.matches("\\d{1,2}:\\d{2}")) {
            throw new IllegalArgumentException("Invalid timeSpent format. Use HH:mm (1:07)");
        }
        String[] parts = timeSpent.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Invalid timeSpent values. Use valid hours and minutes.");
        }
        return LocalTime.of(hours, minutes);
    }

    private Task findTaskById(Long id) {
        Optional<Task> taskOptional = taskRepo.findById(id);
        if (taskOptional.isPresent()) {
            return taskOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Task not found");
        }
    }

    public List<TaskResponseDto> getTasksByProjectId(Long id) {
        List<Task> taskList = taskRepo.findAllByProjectId(id);
        return taskList.stream()
                .map(taskMapper::toTaskResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}

