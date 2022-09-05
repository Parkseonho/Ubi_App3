package com.example.todolist.controller;

import com.example.todolist.domain.Files;
import com.example.todolist.service.FilesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class FilesController {

    private final FilesService todoService;

    FilesController(FilesService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    @ResponseBody
    public List<Files> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("/todos")
    @ResponseBody
    public List<Files> createTodos(@RequestBody Files todo){

        todoService.createTodo(todo);
        return todoService.getTodos();
    }
}
