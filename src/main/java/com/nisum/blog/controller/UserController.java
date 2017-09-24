package com.nisum.blog.controller;

import com.nisum.blog.domain.User;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String findAll(Model postModel) {
        postModel.addAttribute("posts", userService.findAll());
        return "users";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createView(Model postModel) {
        postModel.addAttribute("user", new User());
        postModel.addAttribute("users", userService.findAll());
        return "users/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(User post){
        userService.create(post);
        return "redirect:/users/";
    }
}
