package com.example.todolist.service;

import com.example.todolist.dao.FilesRepository;
import com.example.todolist.domain.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FilesRepository todoRepository;

    public Files createTodo(Files todo) {
        Files newTodo = new Files();
        todoRepository.save(newTodo);

        return newTodo;
    }

    public List<Files> getTodos() {
        return todoRepository.findAll();
    }
}
