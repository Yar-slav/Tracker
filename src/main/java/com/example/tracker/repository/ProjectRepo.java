package com.example.tracker.repository;

import com.example.tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
//    Optional<Project> findById(Long id);
}
