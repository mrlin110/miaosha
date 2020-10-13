package com.mrlin.miaosha.service.impl;

import com.mrlin.miaosha.dao.UserDao;
import com.mrlin.miaosha.po.User;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public User getById() {
        return    userDao.getById(1);
    }

    @Override
    public void addUser() {
        User user =new User(2,"bbc");
        redisUtils.set("user1",user);
    }
}
