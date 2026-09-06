package com.challenge.desafio_estagio.controller;
import com.challenge.desafio_estagio.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaksController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTask(@RequestParam(required = false) String responsavel){
        if(responsavel != null){
            return ResponseEntity.ok(taskRepository.findByResponsavelContainingIgnoreCase(responsavel));
        }
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping("/data")
    public ResponseEntity<List<Task>> listTaskByDataEntrega(@RequestParam LocalDateTime dataEntrega){
        return ResponseEntity.ok(taskRepository.findByDataEntrega(dataEntrega));
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<Task>> listTaskPendentes(@RequestParam(required = false) String responsavel){
        if(responsavel != null){
            return ResponseEntity.ok(taskRepository.findByConcluidaFalseAndResponsavelContainingIgnoreCase(false, responsavel));
        }
        return ResponseEntity.ok(taskRepository.findByConcluidaFalse(false));
    }
}
