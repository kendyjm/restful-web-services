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
    public ResponseEntity<User> retrieveUser(@PathVariable int id)
    {
        User user = service.findOne(id);
        if ( user == null ) {
            throw new UserNotFoundException("id-"+id); // see @ResponseStatus(HttpStatus.NOT_FOUND) in this class
        }

        return ResponseEntity.ok(user);
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
