package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("user", new User());
        return "search";
    }

    @RequestMapping(path = "/searchuser", method = RequestMethod.GET)
    public String searchUser(Model model) {
        model.addAttribute("user", new User());
        return "searchUser";
    }

    @RequestMapping(path = "/searchcomment", method = RequestMethod.GET)
    public String searchComment(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("user", new User());
        return "searchComment";
    }
}
