package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    // GET /users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsersUsers() {
        return service.findAll();
    }

    // GET /users/{id}
    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    // CREATED
    // input - details of user
    // output - created & return the created URI
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        URI myLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(myLocation).build();
    }

}
