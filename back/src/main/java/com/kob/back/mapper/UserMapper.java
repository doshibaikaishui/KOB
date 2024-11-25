package com.kob.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.back.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
