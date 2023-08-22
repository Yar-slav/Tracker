package com.example.tracker.repository;

import com.example.tracker.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findAllByProjectId(Long id);
}
