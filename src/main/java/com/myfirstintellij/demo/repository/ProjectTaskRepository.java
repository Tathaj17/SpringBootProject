package com.myfirstintellij.demo.repository;

import com.myfirstintellij.demo.model.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectTaskRepository extends JpaRepository <ProjectTask,Long> {

  // @Query(value = "select * FROM Project p WHERE p.status =:status and p.summary=:summary", nativeQuery = true)
   List<ProjectTask> findByStatusAndSummary(String status,String summary);
}
