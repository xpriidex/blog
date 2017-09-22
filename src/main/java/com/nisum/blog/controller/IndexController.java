package com.nisum.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}