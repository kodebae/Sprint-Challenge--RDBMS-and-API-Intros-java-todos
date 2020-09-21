package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.UserService;
import com.lambdaschool.todos.views.UserNameCountTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * The entry point for clients to access user data
 */
@RestController
@RequestMapping("/users")
public class UserController
{
    /**
     * Using the User service to process user data
     */
    @Autowired
    private UserService userService;

    /**
     * Returns a list of all users
     * <br>Example: <a href="http://localhost:2019/users/users">http://localhost:2019/users/users</a>
     *
     * @return JSON list of all users with a status of OK
     * @see UserService#findAll() UserService.findAll()
     */
    @GetMapping(value = "/users",
        produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers,
            HttpStatus.OK);
    }

    /**
     * Returns a single user based off a user id number
     * <br>Example: <a href="http://localhost:2019/users/user/7">http://localhost:2019/users/user/7</a>
     *
     * @param userId The primary key of the user you seek
     * @return JSON object of the user you seek
     * @see UserService#findUserById(long) UserService.findUserById(long)
     */
    @GetMapping(value = "/user/{userId}",
        produces = {"application/json"})
    public ResponseEntity<?> getUserById(
        @PathVariable
            Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    /**
     * Given a complete User Object, create a new User record and accompanying todos records
     * <br> Example: <a href="http://localhost:2019/users/user">http://localhost:2019/users/user</a>
     *
     * @param newuser A complete new user to add including todos.
     * @return A location header with the URI to the newly created user and a status of CREATED
     * @throws URISyntaxException Exception if something does not work in creating the location header
     * @see UserService#save(User) UserService.save(User)
     */
    @PostMapping(value = "/user",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewUser(
        @Valid
        @RequestBody
            User newuser) throws URISyntaxException
    {
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{userid}")
            .buildAndExpand(newuser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    /**
     * Deletes a given user along with associated todos.
     * <br>Example: <a href="http://localhost:2019/users/user/14">http://localhost:2019/users/user/14</a>
     *
     * @param id the primary key of the user you wish to delete
     * @return Status of OK
     */
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(
        @PathVariable
            long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gathers list of users and their number of non completed tasks!
     *
     * @return List of usernames, count of non completed tasks
     */
    @GetMapping(value = "/users/todos", produces = {"application/json"})
    public ResponseEntity<?> getUserNameCountTodos()
    {
        List<UserNameCountTodos> myList = userService.getCountUserTodos();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
}