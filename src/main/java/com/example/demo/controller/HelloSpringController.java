package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloSpringController {

    @GetMapping()
    ResponseEntity<?> sayHello() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", "Hello Spring! update 22h48 31/07 - update 22h55 31/07");
        result.put("message", "Success");
        result.put("status", 200);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}