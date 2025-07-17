package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Async("customTaskExecutor")
    public void sendEmail(String email) {
        try {
            String randNumber = Math.random() + "";
            System.out.println(randNumber + "Sending email to " + email + " on thread: " + Thread.currentThread().getName());
            Thread.sleep(3000); // Giả lập gửi email mất 3 giây
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}