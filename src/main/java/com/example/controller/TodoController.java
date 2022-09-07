package com.example.controller;

import com.example.domain.Todo;
import com.example.service.TodoService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping(value = "/todos")
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping("")
    public List<Todo> createTodos(@RequestBody Todo todo){

        todoService.createTodo(todo);
        return todoService.getTodos();
    }

    @DeleteMapping("{id}")
    public List<Todo> deleteTodos(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {

        System.out.println("id: " + id);
        todoService.deleteTodo(id, response);
        return todoService.getTodos();
    }

    @PatchMapping("{id}")
    public List<Todo> checkTodo(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        todoService.checkTodo(id, response);
        return todoService.getTodos();
    }

    @PatchMapping("/edit/{id}")
    public List<Todo> editTodoById(@PathVariable("id") Integer id, @RequestBody Map<String, Object> params, HttpServletResponse response) throws IOException {
        todoService.editTodoById(id, params.get("content"), response);
        return todoService.getTodos();
    }
}


