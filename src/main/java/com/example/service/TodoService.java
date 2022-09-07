package com.example.service;

import com.example.dao.TodoRepository;
import com.example.domain.Todo;
import com.example.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setContent(todo.getContent());
        newTodo.setChecked(false);
        todoRepository.save(newTodo);

        return newTodo;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }


    public void deleteTodo(Integer id, HttpServletResponse response) throws IOException {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            todoRepository.deleteById(id);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode("F-1");
            responseDTO.setMessage("이미 삭제된 할 일입니다.");
            response.setStatus(404);
            response.setHeader("content-type", "application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDTO);

        }
    }

    public void checkTodo(Integer id, HttpServletResponse response) throws IOException {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()) {
            Todo targetTodo = todo.get();
            targetTodo.setChecked(!targetTodo.getChecked());
            todoRepository.save(targetTodo);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode("F-2");
            responseDTO.setMessage("없는 할 일 입니다.");
            response.setStatus(404);
            response.setHeader("content-type", "application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDTO);
        }


    }

    public void editTodoById(Integer id, Object content, HttpServletResponse response) throws IOException {
        Optional<Todo> targetTodo = todoRepository.findById(id);
        if(targetTodo.isPresent()) {
            Todo todo = targetTodo.get();
            todo.setContent((String)content);
            todoRepository.save(todo);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode("F-3");
            responseDTO.setMessage("없는 할 일 입니다.");
            response.setStatus(404);
            response.setHeader("content-type", "application/json;charset=utf-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDTO);
        }
    }
}
