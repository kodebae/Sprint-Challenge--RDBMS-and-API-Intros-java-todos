package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todos;
//import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todorepos;

//    @Autowired
//    UserService userService;

//    @Transactional
//    @Override
//    public Todos update(long todoid)
//    {
//        Todos currentTodo = todorepos.findById(todoid)
//                .orElseThrow(()-> new EntityNotFoundException("Todo " + todoid + " Not Found"));
//
//        currentTodo.setCompleted(true);
//        return todorepos.save(currentTodo);
//    }

    @Override
    public void markComplete(long todoid) {
        Todos t = todorepos.findById(todoid).orElseThrow(() -> new EntityNotFoundException("Todo " + todoid + " Not Found"));
        t.setCompleted(true);
    }

}
