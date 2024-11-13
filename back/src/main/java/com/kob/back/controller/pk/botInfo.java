package com.kob.back.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/pk/")
public class botInfo {

    @RequestMapping("getBotInfo/")
    public Map<String,String> getBotInfo() {

        Map<String,String> bot1 = new HashMap<>();
        bot1.put("botName", "bot1");
        bot1.put("botVersion", "1.0.0");
        bot1.put("attack","10");

        return bot1;
    }
}
