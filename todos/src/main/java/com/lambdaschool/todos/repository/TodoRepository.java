package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todos, Long> {
//    Todos findByNameIgnoreCase( String name);
}


