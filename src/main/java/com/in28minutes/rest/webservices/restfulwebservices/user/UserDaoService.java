package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
        usersCount = 3;
    }

    public List<User> findAll() {
        return  users;
    }

    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    /**
     *
     * @param id
     * @return deleted user
     */
    public User delete(int id) {
        //users.removeIf(user -> user.getId() == id); not so efficient? see implementation
        Iterator<User> iterator = users.iterator();
        User userDeleted = null;
        while(iterator.hasNext()) {
            User user = iterator.next();
            if( user.getId() == id ) {
                userDeleted = user;
                iterator.remove();
            }
        }
        return userDeleted;
    }
}
