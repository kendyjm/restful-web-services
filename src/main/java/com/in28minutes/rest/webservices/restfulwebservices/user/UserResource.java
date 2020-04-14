package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public EntityModel<User> retrieveUser(@PathVariable int id)
    {
        User user = service.findOne(id);
        if ( user == null ) {
            throw new UserNotFoundException("id-"+id); // see @ResponseStatus(HttpStatus.NOT_FOUND) in this class
        }

        // HATEOAS
        // add link to "all-users" to the response which should be useful : SERVER_PATH + "/users"
        // BUT hard code is bad, make it dynamic with the starter-hateoas to retrieve the link resource of method retrieveAllUsersUsers
        // https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals.obtaining-links.builder.methods
        EntityModel<User> userEntityModel = new EntityModel<>(user);
        Link link = linkTo(methodOn(UserResource.class).retrieveAllUsersUsers()).withRel("all-users");
        userEntityModel.add(link);

        return userEntityModel;
    }



    // CREATED
    // input - details of user
    // output - created & return the created URI
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI myLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(myLocation).build();
    }

    // GET /users/{id}
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user = service.delete(id);
        if ( user == null ) {
            throw new UserNotFoundException("id-"+id); // see @ResponseStatus(HttpStatus.NOT_FOUND) in this class
        }
    }

}
