package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    @Autowired
    private PostService postService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Post> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete");
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Integer> create(@RequestBody Post post) {
        int id = postService.create(post);
        return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Integer> update(@RequestBody Post post) {
        int result = postService.update(post);

        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/content/{content}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Post>> findByContent(@PathVariable("content") String content) {
        List<Post> result = postService.findAllByContent(content);
        return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/title/{title}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Post>> findByTitle(@PathVariable("title") String title) {
        List<Post> result = postService.findAllByTitle(title);
        return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/alias/{alias}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Post>> findByAlias(@PathVariable("alias") String alias) {
        List<Post> result = postService.findAllByAuthorsAlias(alias);

        return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/bydate/", params = {"date"}, method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByDate(@RequestParam("date") String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateTime = formatter.parseDateTime(date);
        dateTime.withTimeAtStartOfDay();

        List<Post> result = postService.findByDate(dateTime);

        return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/byrange/", params = {"dateFrom", "dateTo"}, method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findByDateRange(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateTo") String dateTo) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateQuery1 = formatter.parseDateTime(dateFrom);
        DateTime dateQuery2 = formatter.parseDateTime(dateTo);
        dateQuery1.withTimeAtStartOfDay();
        dateQuery2.withTimeAtStartOfDay();

        List<Post> result = postService.findByDateRange(dateQuery1, dateQuery2);

        return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
    }
}
