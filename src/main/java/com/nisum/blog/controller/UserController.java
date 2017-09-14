package com.nisum.blog.controller;

import com.nisum.blog.domain.User;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
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
        return new ResponseEntity<Integer>(userId, HttpStatus.OK);
        //http://localhost:8080/users/?firstName=Jojo&lastName=Junes&alias=Jeans&bio=myBio&email=myEmail@gmail.com&image=laFotoBkn.jpg
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
        //http://localhost:8080/users/1
    }
}
