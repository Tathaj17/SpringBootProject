package com.myfirstintellij.demo.service;

import com.myfirstintellij.demo.model.ProjectTask;
import com.myfirstintellij.demo.repository.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    public ProjectTask saveOrUpdateProjectTask(ProjectTask projectTask)
    {
        if(projectTask.getStatus()==null || projectTask.getStatus()=="")
        {
           projectTask.setStatus("TO_DO");
        }
        return (ProjectTask) projectTaskRepository.save(projectTask);
    }
    public List<ProjectTask> allProjects()
    {
        return projectTaskRepository.findAll();
    }
    public Optional<ProjectTask> getSpecificProject(Long id)
    {
        Optional<ProjectTask> pTask= Optional.ofNullable(projectTaskRepository.findById(id).orElse(null));
        return pTask;
    }
    public List<ProjectTask> getspecificStatus(String status,String summary)throws Exception

    {

            List<ProjectTask> pTask = projectTaskRepository.findByStatusAndSummary(status,summary);
           return pTask;
    }

    public void deleteSpecificProject(Long id)
    {
        projectTaskRepository.deleteById(id);

    }


}
