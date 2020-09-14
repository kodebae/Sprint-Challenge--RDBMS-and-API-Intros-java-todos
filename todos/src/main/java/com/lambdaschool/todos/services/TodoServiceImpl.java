package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{

    @Autowired
    TodoRepository todorepos;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public Todo update(long todoid)
    {

        Todo currentTodo = todorepos.findById(todoid)
                .orElseThrow(()-> new EntityNotFoundException("Todo " + todoid + " Not Found"));

        currentTodo.setCompleted(true);
        return todorepos.save(currentTodo);


    }


    @Transactional
    @Override
    public Todo save(
            long userid,
            String description)
    {
        User currentUser = userService.findUserById(userid);

        Todo newTodo = new Todo(currentUser,
                description);
        return todorepos.save(newTodo);
    }

    public Todo findTodoById(long id) throws EntityNotFoundException
    {
        return todorepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + id + " not found!"));
    }

}
