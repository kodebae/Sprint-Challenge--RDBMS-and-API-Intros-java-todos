package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todo;

public interface TodoService {
    void markComplete(long todoid);
    Todo save(long userid, String description);

    Todo update(long id);

    Todo findTodoById(long id);
}
