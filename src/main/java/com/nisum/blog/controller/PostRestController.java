package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    @Autowired
    private PostService postService;

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Post> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(path="/", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id){
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete");
    }

    @RequestMapping(path = "/",method = RequestMethod.POST )
    @ResponseBody
    public ResponseEntity<Integer> create(@RequestBody Post post){
        int id = postService.create(post);
        return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT )
    @ResponseBody
    public ResponseEntity<Integer> update(@RequestBody Post post,@PathVariable("id") Integer id){
        post.setId(id);
        int result = postService.update(post);
        return new ResponseEntity<Integer>(result,HttpStatus.OK);
    }
}
