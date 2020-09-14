package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Todo findByNameIgnoreCase( String name);
}
