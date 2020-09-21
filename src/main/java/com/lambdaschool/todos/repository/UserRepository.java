package com.lambdaschool.todos.repository;

import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.views.UserNameCountTodos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The CRUD repository connecting User to the rest of the application
 */
public interface UserRepository extends CrudRepository<User, Long>
{
    /**
     * Custom query to gather the number of current todos users have
     *
     * @return A list of UserNameCountTodos which includes the username and count of non-completed todos
     */
    @Query(value = "SELECT u.username as usernamerpt, count(t.todoid) as counttodos FROM users u JOIN todos t ON u.userid = t.userid WHERE NOT t.completed GROUP BY u.username ORDER BY u.username", nativeQuery = true)
    List<UserNameCountTodos> getCountUserTodos();
}
