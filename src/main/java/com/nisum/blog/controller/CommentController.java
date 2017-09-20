package com.nisum.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @RequestMapping("/")
    public String test (){
        return "comment";

    }

}
