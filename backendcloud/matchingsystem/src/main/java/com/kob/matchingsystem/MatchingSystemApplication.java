package com.kob.matchingsystem;

import com.kob.matchingsystem.service.impl.MatchingServiceImpl;
import com.kob.matchingsystem.service.impl.utils.MatchingPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatchingSystemApplication {
    public static void main(String[] args) {
        MatchingServiceImpl.matchingPool.start();
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}