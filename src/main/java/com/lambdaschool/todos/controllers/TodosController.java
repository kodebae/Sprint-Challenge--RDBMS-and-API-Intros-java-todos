package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The entry point for client to access user, todos combinations
 */
@RestController
@RequestMapping("/todos")
public class TodosController
{
    /**
     * Using the Todos service to process user, todos combinations data
     */
    @Autowired
    TodosService todosService;

    /**
     * Given the todo id, mark the task as complete
     * <br>Example: <a href="http://localhost:2019/todos/todo/7">http://localhost:2019/todos/todo/7</a>
     *
     * @param todoid The todo to be marked complete
     * @return Status of OK
     */
    @PatchMapping(value = "/todo/{todoid}")
    public ResponseEntity<?> completeTodo(@PathVariable long todoid)
    {
        todosService.markComplete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
