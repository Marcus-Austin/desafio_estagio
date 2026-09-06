package com.challenge.desafio_estagio.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.desafio_estagio.model.Task;
public interface TaskRepository extends JpaRepository<Task,Long>{

    List<Task> findByResponsavelContainingIgnoreCase(String responsavel);

    List<Task> findByDataEntrega(LocalDateTime dataEntrega);

    List<Task> findByConcluidaFalse(boolean concluida);

    List<Task> findByConcluidaFalseAndResponsavelContainingIgnoreCase(boolean concluida, String responsavel);
}
