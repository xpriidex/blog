package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.PostService;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String findAll(Model postModel) {
        postModel.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createView(Model postModel) {
        postModel.addAttribute("post", new Post());
        postModel.addAttribute("users", userService.findAll());
        return "posts/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(Post post){
        postService.create(post);
        return "redirect:/posts/";
    }
}
