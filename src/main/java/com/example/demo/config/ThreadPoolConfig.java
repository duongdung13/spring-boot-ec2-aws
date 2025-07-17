package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "customTaskExecutor")
    public Executor customTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);    // Thread tối thiểu
        executor.setMaxPoolSize(50);     // Thread tối đa
        executor.setQueueCapacity(100);  // Độ dài hàng đợi
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("AppExecutor-");
        executor.initialize();
        return executor;
    }
}