package com.kob.back.service.user.account;

import java.util.Map;

public interface RegisterService {
    Map<String,String> register(String username, String password , String confirm_password);
}