package com.nisum.blog.controller;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.CommentService;
import com.nisum.blog.service.PostService;
import com.nisum.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String findAll(Model postModel){
        postModel.addAttribute("comments",commentService.findAll());
        return "comments/list";

    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createView(Model postModel){
        postModel.addAttribute("comment", new Comment());
        postModel.addAttribute("users", userService.findAll());
        postModel.addAttribute("posts", postService.findAll());
        return "comments/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(Comment comment){
        commentService.create(comment);
        return "redirect:/comments/";
    }

    @RequestMapping(path = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") int id, Model postModel){
        Comment comment = commentService.findById(id);
        postModel.addAttribute("comment", comment);
        return "comments/detail";
    }

    @RequestMapping(path = "/findbyalias/{alias}", method = RequestMethod.GET)
    public String findAllByAlias(@PathVariable("alias") String alias,Model postModel) {
        postModel.addAttribute("comments", commentService.findByAuthorAlias(alias));
        return "comments/list";
    }

    @RequestMapping(path = "/findbyauthorid/{authorId}", method = RequestMethod.GET)
    public String findByAuthorId(@PathVariable("authorId") int authorId,Model postModel) {
        postModel.addAttribute("comments", commentService.findByAuthorId(authorId));
        return "comments/list";
    }

    @RequestMapping(path = "/findbypostid/{postId}", method = RequestMethod.GET)
    public String findByPostId(@PathVariable("postId") int postId,Model postModel) {
        postModel.addAttribute("comments", commentService.findByPostId(postId));
        return "comments/list";
    }

    @RequestMapping(path = "/findbyalias", method = RequestMethod.POST)
    public String searchAllByAlias(User user, Model postModel) {
        System.out.println(user.getAlias());
        postModel.addAttribute("comments", commentService.findByAuthorAlias(user.getAlias()));
        return "comments/list";
    }

    @RequestMapping(path = "/findbyauthorid",method = RequestMethod.POST)
    public String searchByAuthorId(User user, Model postModel) {
        System.out.println(user.getId());
        postModel.addAttribute("comments", commentService.findByAuthorId(user.getId()));
        return "comments/list";
    }

    @RequestMapping(path = "/findbypostid", method = RequestMethod.POST)
    public String searchByPostId(Post post, Model postModel) {
        System.out.println(post.getId());
        postModel.addAttribute("comments", commentService.findByPostId(post.getId()));
        return "comments/list";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id){
        commentService.deleteByCommentId(id);
        return "redirect:/comments/";
    }

}
