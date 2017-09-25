package com.nisum.blog.controller;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> findAll(){
        return ok(commentService.findAll());
    }

    @RequestMapping(path = "/findbyauthorid/{authorId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> findByAuthorId(@PathVariable("authorId") Integer id) {
        return new ResponseEntity<List<Comment>>(commentService.findByAuthorId(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/findbypostid{postId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> findByPostId(@PathVariable("id") Integer id) {
        return new ResponseEntity<List<Comment>>(commentService.findByPostId(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(
            @RequestParam("authorId") Integer authorId,
            @RequestParam("body") String body,
            @RequestParam("postId") Integer postId) {
        Comment comment = new Comment();
        comment.setAuthorId(authorId);
        comment.setBody(body);
        comment.setPostId(postId);
        int commentId = commentService.create(comment);
        return new ResponseEntity<Integer>(commentId, HttpStatus.OK);
    }

    @RequestMapping(path = "/",method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<Integer> create(@RequestBody Comment comment){
        int id = commentService.create(comment);
        return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
    }


}
