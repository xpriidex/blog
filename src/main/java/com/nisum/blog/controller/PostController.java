package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.CommentService;
import com.nisum.blog.service.PostService;
import com.nisum.blog.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

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
    public String create(Post post) {
        postService.create(post);
        return "redirect:/posts/";
    }

    @RequestMapping(path = "/read/{id}", method = RequestMethod.GET)
    public String read(@PathVariable("id") int id, Model postModel) {
        Post post = postService.findById(id);
        User user = userService.findById(post.getAuthorId());
        if (post == null || user == null) {
            return "noData";
        }
        postModel.addAttribute("post", post);
        postModel.addAttribute("user", user);
        postModel.addAttribute("comments", commentService.findByPostId(id));
        return "posts/detail";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model postModel) {
        Post post = postService.findById(id);

        if (post.getId()==0) {
            return "noData";
        }

        postModel.addAttribute("post", post);
        return "posts/update";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        Post post = postService.findById(id);

        if (post == null) {
            return "noData";
        }

        postService.delete(id);
        return "redirect:/posts/";
    }

    @RequestMapping(path = "/findbytitle", method = RequestMethod.POST)
    public String findByTitle(Model postModel, Post post) {
        String title = post.getTitle();
        postModel.addAttribute("posts", postService.findAllByTitle(title));

        return "posts/list";
    }

    @RequestMapping(path = "/findbybody", method = RequestMethod.POST)
    public String findByBody(Model postModel, Post post) {
        postModel.addAttribute("posts", postService.findAllByContent(post.getBody()));

        return "posts/list";
    }

    @RequestMapping(path = "/findbyalias/{alias}", method = RequestMethod.GET)
    public String findAllByAlias(Model postModel, @PathVariable("alias") String alias) {
        User user = userService.findByAlias(alias);

        postModel.addAttribute("posts", postService.findAllByAuthorsAlias(user.getAlias()));
        return "posts/list";
    }

    @RequestMapping(path = "/findbyalias", method = RequestMethod.POST)
    public String findAllByAlias(User user, Model postModel) {
        System.out.println(user.getAlias());
        postModel.addAttribute("posts", postService.findAllByAuthorsAlias(user.getAlias()));
        return "posts/list";
    }

    @RequestMapping(path = "/bydate", method = RequestMethod.POST)
    public String findByDate(Model postModel, @RequestParam("date") String date) {
        System.out.println(date);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateTime = formatter.parseDateTime(date);
        dateTime.withTimeAtStartOfDay();

        List<Post> result = postService.findByDate(dateTime);
        postModel.addAttribute("posts", result);


        return "posts/list";
    }

    @RequestMapping(path = "/byrange", method = RequestMethod.POST)
    public String findByDateRange(Model postModel, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateQuery1 = formatter.parseDateTime(dateFrom);
        DateTime dateQuery2 = formatter.parseDateTime(dateTo);
        dateQuery1.withTimeAtStartOfDay();
        dateQuery2.withTimeAtStartOfDay();

        List<Post> result = postService.findByDateRange(dateQuery1, dateQuery2);
        postModel.addAttribute("posts", result);

        return "posts/list";
    }
}
