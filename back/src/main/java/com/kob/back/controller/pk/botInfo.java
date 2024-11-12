package com.kob.back.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/pk/")
public class botInfo {

    @RequestMapping("getBotInfo/")
    public List<Map<String,String>> getBotInfo() {
        List<Map<String,String>>list = new LinkedList<>();
        Map<String,String> bot1 = new HashMap<>();
        bot1.put("botName", "bot1");
        bot1.put("botVersion", "1.0.0");

        Map<String,String> bot2 = new HashMap<>();
        bot2.put("botName", "bot2");
        bot2.put("botVersion", "2.0.0");

        list.add(bot1);
        list.add(bot2);
        return list;
    }
}
