package com.mrlin.miaosha.service.impl;

import com.mrlin.miaosha.dao.UserDao;
import com.mrlin.miaosha.po.User;
import com.mrlin.miaosha.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getById() {
        return    userDao.getById(1);
    }
}
