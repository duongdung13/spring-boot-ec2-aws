package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloSpringController {

    private final EmailService emailService;

    public HelloSpringController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping()
    ResponseEntity<?> sayHello() {
        Map<String, Object> result = new HashMap<>();
        result.put("data", "Hello Spring!");
        result.put("message", "Success");
        result.put("status", 200);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/send-email")
    public String triggerEmail(@RequestParam String email) {
        emailService.sendEmail(email);
        return "Email task submitted!";
    }

}