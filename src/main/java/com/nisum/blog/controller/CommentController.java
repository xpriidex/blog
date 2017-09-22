package com.nisum.blog.controller;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String findAll(Model postModel){
        postModel.addAttribute("comments",commentService.findAll());
        return "comments/list";

    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createView(Model postModel){
        postModel.addAttribute("comment", new Comment());
        return "comments/create";
    }

    /*@RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(Comment comment){
        commentService.create(comment);
        return "redirect:/comments/";
    }*/

}
