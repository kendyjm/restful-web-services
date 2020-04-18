package com.in28minutes.rest.webservices.restfulwebservices.user.jpa;


import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserDaoService;
import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService service;
    @Autowired
    private UserRepository userRepository;

    // GET /users
    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    // GET /users/{id}
    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);
        if ( !user.isPresent() ) {
            throw new UserNotFoundException("id-"+id); // see @ResponseStatus(HttpStatus.NOT_FOUND) in this class
        }

        // HATEOAS
        // add link to "all-users" to the response which should be useful : SERVER_PATH + "/users"
        // BUT hard code is bad, make it dynamic with the starter-hateoas to retrieve the link resource of method retrieveAllUsersUsers
        // https://docs.spring.io/spring-hateoas/docs/current/reference/html/#fundamentals.obtaining-links.builder.methods
        /* 2.2.6 version of HATEOAS  (latest version)
        EntityModel<User> userEntityModel = new EntityModel<>(user);
        Link link = linkTo(methodOn(UserResource.class).retrieveAllUsersUsers()).withRel("all-users");
        userEntityModel.add(link);return userEntityModel;
         */
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }



    // CREATED
    // input - details of user
    // output - created & return the created URI
    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI myLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(myLocation).build();
    }

    // GET /users/{id}
    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        User user = service.delete(id);
        if ( user == null ) {
            throw new UserNotFoundException("id-"+id); // see @ResponseStatus(HttpStatus.NOT_FOUND) in this class
        }
    }

}
