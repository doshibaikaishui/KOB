package com.kob.matchingsystem.service.impl.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MatchingPool extends Thread{
    private static List<Player> players = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();

    private static RestTemplate restTemplate;

    private final static String startGameUrl = "http://127.0.0.1:3000/pk/start/game/";

    @Autowired
    public void sendRestTemplate(RestTemplate restTemplate) {
        MatchingPool.restTemplate = restTemplate;
    }

    public void addPlayer(Integer userId, Integer rating, Integer botId) {
        lock.lock();
        try {
            boolean playerExists = players.stream()
                    .anyMatch(p -> p.getUserId().equals(userId));
            if (!playerExists) {
                players.add(new Player(userId, rating, botId, 0));
            } else {
                throw new IllegalArgumentException("玩家已存在于匹配池中");
            }
        } finally {
            lock.unlock();
        }
    }

    public void removePlayer(Integer userId) {
        lock.lock();
        try {
            List<Player> newPlayers = new ArrayList<>();
            for (Player player: players) {
                if(!player.getUserId().equals(userId)) {
                    newPlayers.add(player);
                }
            }
            players = newPlayers;
        } finally {
            lock.unlock();
        }
    }

    // 等待时间加1
    private void increaseWaitingTime() {
        for (Player player: players) {
            player.setWaitingTime(player.getWaitingTime() + 1);
        }
    }

    // 检查是否匹配
    private boolean checkMatched(Player player1, Player player2) {
        int ratingDelta = Math.abs(player1.getRating() - player2.getRating());
        int waitingTime = Math.min(player1.getWaitingTime(), player2.getWaitingTime());
        return ratingDelta <= waitingTime * 10;
    }

    private void sendResult(Player a,Player b) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("a_bot_id", a.getBotId().toString());
        data.add("b_id", b.getUserId().toString());
        data.add("b_bot_id", b.getBotId().toString());
        restTemplate.postForObject(startGameUrl, data, String.class);
    }

    private void matchPlayers() {
        boolean[] used =new boolean[players.size()];
        for (int i = 0; i < players.size(); i++) {
            if(used[i]) continue;
            Player player1 = players.get(i);
            for (int j = i + 1; j < players.size(); j++) {
                if(used[j]) continue;
                Player player2 = players.get(j);
                if(checkMatched(player1, player2)) {
                    used[i] = true;
                    used[j] = true;
                    sendResult(player1, player2);
                    break;
                }
            }
        }

        List<Player> newPlayers = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if(!used[i]) {
                newPlayers.add(players.get(i));
            }
        }
        players = newPlayers;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                lock.lock();
                try {
                    increaseWaitingTime();
                    matchPlayers();
                } finally {
                    lock.unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }


}
