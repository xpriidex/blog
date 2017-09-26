package com.nisum.blog.controller;

import com.nisum.blog.domain.User;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String findAll(Model userModel) {
        userModel.addAttribute("users", userService.findAll());
        return "users/list";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createView(Model userModel) {
        userModel.addAttribute("user", new User());
        userModel.addAttribute("users", userService.findAll());
        return "users/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(User user){
        userService.create(user);
        return "redirect:/users/";
    }

    @RequestMapping(path = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") int id, Model postModel){
        User user = userService.findById(id);
        postModel.addAttribute("user", user);
        return "users/detail";
    }

    @RequestMapping(path = "/findbyalias", method = RequestMethod.POST)
    public String findByAlias(User user, Model postModel){
        User userByAlias = userService.findByAlias(user.getAlias());
        if (userByAlias == null)
        {
            userByAlias = new User();
        }
        postModel.addAttribute("user", userByAlias);
        return "users/detail";
    }

    @RequestMapping(path = "/findbyemail", method = RequestMethod.POST)
    public String findByEmail(User user, Model postModel){
        User userByEmail = userService.findByEmail(user.getEmail());
        if (userByEmail == null)
        {
            userByEmail = new User();
        }
        postModel.addAttribute("user", userByEmail);
        return "users/detail";
    }

    @RequestMapping(path = "/findbyfirstname", method = RequestMethod.POST)
    public String findByFirstName(User user, Model postModel){
        List<User> userList = userService.findByFirstName(user.getFirstName());
        postModel.addAttribute("users", userList);
        return "users/list";
    }

    @RequestMapping(path = "/findbylastname", method = RequestMethod.POST)
    public String findByLastName(User user, Model postModel){
        List<User> userList = userService.findByLastName(user.getLastName());
        postModel.addAttribute("users", userList);
        return "users/list";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model postModel){
        postModel.addAttribute("user", userService.findById(id));
        return "users/update";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id){
        userService.delete(id);
        return "redirect:/users/";
    }
}
