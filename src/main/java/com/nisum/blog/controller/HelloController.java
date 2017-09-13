package com.nisum.blog.controller;

import com.nisum.blog.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("hello")
    public ResponseEntity<String> helloWorld(){
        String message = helloService.greet();
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
