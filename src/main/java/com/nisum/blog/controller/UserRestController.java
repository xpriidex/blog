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

    @RequestMapping(path = "/first_name", method = RequestMethod.PUT)
    public ResponseEntity updateFirstName(@RequestBody User user) {
        userService.updateFirstName(user.getId(), user.getFirstName());
        return ok("first name updated");
    }

    @RequestMapping(path = "/last_name", method = RequestMethod.PUT)
    public ResponseEntity updateLastName(@RequestBody User user) {
        userService.updateLastName(user.getId(), user.getLastName());
        return ok("lasts name updated");
    }

    @RequestMapping(path = "/email", method = RequestMethod.PUT)
    public ResponseEntity updateEmail(@RequestBody User user) {
        userService.updateEmail(user.getId(), user.getEmail());
        return ok("email updated");
    }

    @RequestMapping(path = "/alias", method = RequestMethod.PUT)
    public ResponseEntity updateAlias(@RequestBody User user) {
        userService.updateAlias(user.getId(), user.getAlias());
        return ok("alias updated");
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
