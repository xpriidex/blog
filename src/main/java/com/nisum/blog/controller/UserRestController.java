package com.nisum.blog.controller;

import com.nisum.blog.domain.User;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        return ok(userService.findAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        return ok(userService.findById(id));
    }

    @RequestMapping(path = "/alias/{alias}", method = RequestMethod.GET)
    public ResponseEntity<User> findByAlias(@PathVariable("alias") String alias) {
        return ok(userService.findByAlias(alias));
    }

    @RequestMapping(path = "/firstname/{firstName}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findByFirstName(@PathVariable("firstName") String firstName) {
        return ok(userService.findByFirstName(firstName));
    }

    @RequestMapping(path = "/lastname/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findByLastName(@PathVariable("lastName") String lastName) {
        return ok(userService.findByLastName(lastName));
    }

    @RequestMapping(path = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        return ok(userService.findByEmail(email));
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public ResponseEntity updateFirstName(@RequestBody User user) {
        int rowsUpdated = userService.update(user);
        return ok(rowsUpdated);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@RequestBody User user) {
        int userId = userService.create(user);
        return ok(userId);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return ok("deleted");
    }
}
