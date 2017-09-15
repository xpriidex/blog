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
public class UserController {

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
    public ResponseEntity<User> findById(@PathVariable("alias") String alias) {
        return ok(userService.findByAlias(alias));
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
        //http://localhost:8080/users/1
    }

    @RequestMapping(path = "/first_name", method = RequestMethod.PUT)
    public ResponseEntity updateFirstName(@RequestBody User user) {
        userService.updateFirstName(user.getId(), user.getFirstName());
        return ok("updated");
    }

    @RequestMapping(path = "/last_name", method = RequestMethod.PUT)
    public ResponseEntity updateLastName(@RequestBody User user) {
        userService.updateLastName(user.getId(), user.getLastName());
        return ok("updated");
    }

    @RequestMapping(path = "/email", method = RequestMethod.PUT)
    public ResponseEntity updateEmail(@RequestBody User user) {
        userService.updateEmail(user.getId(), user.getEmail());
        return ok("updated");
    }

    @RequestMapping(path = "/alias", method = RequestMethod.PUT)
    public ResponseEntity updateAlias(@RequestBody User user) {
        userService.updateAlias(user.getId(), user.getAlias());
        return ok("updated");
    }
}
