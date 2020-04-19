package com.in28minutes.rest.webservices.restfulwebservices.user.jpa;


import com.in28minutes.rest.webservices.restfulwebservices.user.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.User;
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
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

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
        User savedUser = userRepository.save(user);
        URI myLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(myLocation).build(); // see the 'Location' key in header !
    }

    // DELETE /users/{id}
    @DeleteMapping(path = "/jpa/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id)
    {
        userRepository.deleteById(id);
        // 204 HTTPSTATUS No Content : The request succeeded but there is nothing to show. Usually sent after a successful DELETE.
        return ResponseEntity.noContent().build();
    }


    ///// POSTS
    // GET users posts
    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsOfAUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }

        return user.get().getPosts();
    }

    // created a post for a user
    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("id-"+id);
        }

        post.setUserPoster(user.get());

        Post savedPost = postRepository.save(post);
        URI myLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(myLocation).build(); // see the 'Location' key in header !
    }
}
