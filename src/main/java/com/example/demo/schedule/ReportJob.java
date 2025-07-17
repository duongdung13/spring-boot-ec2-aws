package com.example.demo.schedule;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportJob {


    @Scheduled(cron = "0 */1 * * * *")  // Mỗi phút chạy 1 lần
    public void generateReport() {
        System.out.println("::: Scheduled Job ::: Generating report at " + LocalDateTime.now());
    }
}
