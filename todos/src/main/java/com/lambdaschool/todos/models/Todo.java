package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity // how we interact with our table
@Table(name= "todos") // connecting to the todos
public class Todo extends Auditable { // allow us to manipulate the data we need to update our todos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid; // our unique id for our individual id's

    @Column(nullable = false)
    private String description; // our string description of the tasks

    private boolean completed = false; // completed is default as false, cause we need it to be not completed when created


    @ManyToOne // the opposite should be true for user, one user to many tasks becasue they can have more than one task to do
    @JoinColumn(name = "userid", nullable = false) //many to one user for many tasks to one user
    @JsonIgnoreProperties(value = "todos", allowSetters = true)
    private User user; // our users are private and should have login information to access the database

    public Todo() { //default constructor stays empty
    }

    public Todo(User user, String description) { // use this constructor to initialize the object of our class
        this.description = description;
        this.user = user;
    }
// Our getters and setters

    public long getTodoid() {
        return todoid;
    }

    public void setTodoid(long todoid) {
        this.todoid = todoid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}


/**- [ ] TODOS
 - `todoid` primary key, not null long
 - `description` string, not null
 - `completed` boolean. Note that for all new todos, default completed to false
 - `userid` foreign key (one user to many todos) not null
 - All tables must have the standard 4 auditing fields in place and working, being populated: created on, created by, last modified on, last modified by. The auditing usernames will all default to `llama`.
 - USERS have a one to many relationship with TODOS.

 - [ ] SeedData.java is a sample class that can be modified to populate the database. However, this is the seed data to use for this application. The structure can change, the data should not change. Do populate the database with this data.

 The following end points are already available in the initial application. You are to make sure the end points work with the newly added todos table

 - [ ] GET /users/users - return all of the users and their todos.

 - [ ] GET /users/user/{userid} - return the user and their todos based off of user id.

 - [ ] POST /users/user - adds a user with their todos

 - [ ] PATCH /todos/todo/{todoid} - mark a todo as completed.

 - [ ] DELETE /users/user/{userid} - Deletes a user based off of their userid and deletes all their associated todos.
 **/

/**
 * The entity allowing interaction with the users table
 */
