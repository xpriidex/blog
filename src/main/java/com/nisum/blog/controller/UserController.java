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

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("alias") String alias,
            @RequestParam("bio") String bio,
            @RequestParam("email") String email,
            @RequestParam("image") String image) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setAlias(alias);
        newUser.setBio(bio);
        newUser.setEmail(email);
        newUser.setImage(image);
        int userId = userService.create(newUser);
        return ok(userId);
        //http://localhost:8080/api/users/?firstName=Jojo&lastName=Junes&alias=Jeans&bio=myBio&email=myEmail@gmail.com&image=laFotoBkn.jpg
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return ok("deleted");
        //http://localhost:8080/users/1
    }

    @RequestMapping(path = "/update/first_name", method = RequestMethod.PUT)
    public ResponseEntity updateFirstName(
            @RequestParam("id") Integer id,
            @RequestParam("firstName") String firstName) {
        userService.updateFirstName(id, firstName);
        return ok("updated");
        //http://localhost:8080/api/users/update?id=1&firstName=Orlando
    }

    @RequestMapping(path = "/update/last_name", method = RequestMethod.PUT)
    public ResponseEntity updateLastName(
            @RequestParam("id") Integer id,
            @RequestParam("lastName") String lastName) {
        userService.updateLastName(id, lastName);
        return ok("updated");
        //http://localhost:8080/api/users/update?id=1&firstName=Orlando
    }

    @RequestMapping(path = "/update/email", method = RequestMethod.PUT)
    public ResponseEntity updateEmail(
            @RequestParam("id") Integer id,
            @RequestParam("email") String email) {
        userService.updateEmail(id, email);
        return ok("updated");
        //http://localhost:8080/api/users/update/email/?id=1&email=Soko

    }

    @RequestMapping(path = "/update/alias", method = RequestMethod.PUT)
    public ResponseEntity updateAlias(
            @RequestParam("id") Integer id,
            @RequestParam("alias") String alias) {
        userService.updateAlias(id, alias);
        return ok("updated");
        //http://localhost:8080/api/users/update/alias/?id=1&alias=Soko
    }
}
