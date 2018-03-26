package com.patis.wms.controller;

import com.patis.wms.dto.TaskDTO;
import com.patis.wms.dto.WorkerDTO;
import com.patis.wms.entity.*;
import com.patis.wms.service.*;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    DistributionService distributionService;




    @GetMapping("/")
    ResponseEntity<List<TaskDTO>> findAll(
            @RequestParam("id_worker") Optional<Long> id_worker,
            @RequestParam("current") Optional<Boolean> current
    ){

        List<Task> tasks;
        if (id_worker.isPresent()){
            tasks = taskService.findByWorker(id_worker.get());
        }else{
            tasks = taskService.findAll();
        }

        List<TaskDTO> result = tasks.stream().map(TaskDTO::new)
                .filter(t ->!current.isPresent() || ( current.get() && (t.getTaskStatus() == TaskStatus.IN_WORK || t.getTaskStatus() == TaskStatus.READY)) )
                .collect(Collectors.toList());
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id_task}/start/")
    void putDistributionInWork(
        @PathVariable("id_task") long id_task
    ){

        Task task = taskService.findOne(id_task);
        task.setTaskStatus(TaskStatus.IN_WORK);
        taskService.save(task);

    }

    @PostMapping("/{id_task}/complete/{id_distribution}/")
    void completedistribution(
            @PathVariable("id_task") long id_task,
            @PathVariable("id_distribution") long id_distribution
    ){

        Task task = taskService.findOne(id_task);

        task
                .getTaskItems().stream()
                .flatMap(taskItem -> taskItem.getDistributions().stream())
                .filter(distribution -> distribution.getId() == id_distribution)
                .findFirst().ifPresent(distribution -> distribution.setDone(true));

        long remained = task
                .getTaskItems().stream()
                .flatMap(taskItem -> taskItem.getDistributions().stream())
                .filter(distribution -> !distribution.isDone()).count();

        if (remained == 0){
            task.setTaskStatus(TaskStatus.DONE);
            task.setTimeEnd(LocalDateTime.now());
        }

        taskService.save(task);

    }






}
