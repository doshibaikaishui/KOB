package com.kob.back.service.impl.user.account;


import com.kob.back.pojo.User;
import com.kob.back.service.impl.utils.UserDetailsImpl;
import com.kob.back.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> info = new HashMap<>();
        info.put("error_message","success");
        info.put("id",user.getId().toString());
        info.put("username",user.getUsername());
        info.put("photo",user.getPhoto());
        return info;
    }
}
