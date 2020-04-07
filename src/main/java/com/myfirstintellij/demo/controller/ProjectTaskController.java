package com.myfirstintellij.demo.controller;

import com.myfirstintellij.demo.model.ProjectTask;
import com.myfirstintellij.demo.service.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProjectTaskController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @GetMapping("/")
    public  ResponseEntity<?> getAllProjects()
    {
        List<ProjectTask> pTask= projectTaskService.allProjects();
        return new ResponseEntity<Object>(pTask,HttpStatus.OK);

    }
    @PostMapping("/insert")
    public ResponseEntity<?> insert(@Valid   @RequestBody ProjectTask projectTask, BindingResult result)
    {
        if(result.hasErrors())
        {
            HashMap<String,String> errorMap= new HashMap<>();
            for(FieldError fd:result.getFieldErrors())
            {
                errorMap.put(fd.getField(),fd.getDefaultMessage()) ;
            }
            return  new ResponseEntity<HashMap<String,String> >(errorMap, HttpStatus.BAD_REQUEST);
        }
        ProjectTask projectTask1= projectTaskService.saveOrUpdateProjectTask(projectTask);
        return  new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }
    @PutMapping("/insert")
    public ResponseEntity<?> saveOrUpdate(@Valid  @RequestBody ProjectTask projectTask, BindingResult result)
    {
        if(result.hasErrors())
        {
            HashMap<String,String> errorMap= new HashMap<>();
            for(FieldError fd:result.getFieldErrors())
            {
                errorMap.put(fd.getField(),fd.getDefaultMessage()) ;
            }
            return  new ResponseEntity<HashMap<String,String> >(errorMap, HttpStatus.BAD_REQUEST);
        }
        ProjectTask projectTask1= projectTaskService.saveOrUpdateProjectTask(projectTask);
        return  new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }
    @GetMapping("id/{id}")
    public ResponseEntity<?> specificProject(@PathVariable Long id)
    {
        Optional<ProjectTask> pTask=projectTaskService.getSpecificProject(id);
        return  new ResponseEntity<Optional<ProjectTask>>(pTask, HttpStatus.FOUND);
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteSpecificProject(@PathVariable Long id)
    {
        projectTaskService.deleteSpecificProject(id);
        return  new ResponseEntity<String>("Project Deleted", HttpStatus.FOUND);
    }
    @GetMapping("status/{status}/summary/{summary}")
    public ResponseEntity<?> specificProjectWithStatus(@PathVariable String status,@PathVariable String summary) throws Exception {
        List<ProjectTask> pTask1 = null;
        try {
            pTask1= projectTaskService.getspecificStatus(status,summary);
           if(pTask1==null){
               return  new ResponseEntity<Object>(pTask1, HttpStatus.NOT_FOUND);
           }
        }catch(Exception e){
            System.out.println("Not found");
           // System.out.println("Not found");
        }
         System.out.println(pTask1);
        return  new ResponseEntity<Object>(pTask1, HttpStatus.FOUND);
    }


}
