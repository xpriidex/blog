package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.PostService;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(path = "/findbyalias/{alias}", method = RequestMethod.GET)
    public String findAllByAlias(@PathVariable("alias") String alias,Model postModel) {
        postModel.addAttribute("posts", postService.findAllByAuthorsAlias(alias));
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

    @RequestMapping(path = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") int id, Model postModel){
        Post post = postService.findById(id);
        User user = userService.findById(post.getAuthorId());
        postModel.addAttribute("post", post);
        postModel.addAttribute("user", user);
        return "posts/detail";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model postModel){
        postModel.addAttribute("post", postService.findById(id));
        return "posts/update";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id){
        postService.delete(id);
        return "redirect:/posts/";
    }

    @RequestMapping(path = "/update/", method = RequestMethod.POST)
    public String update(Post post){
        postService.update(post);
        return "redirect:/posts/read/" + post.getId();
    }
}
