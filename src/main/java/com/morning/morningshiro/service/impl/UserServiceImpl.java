package com.morning.morningshiro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.morning.morningshiro.dao.entity.UserEntity;
import com.morning.morningshiro.dao.mapper.UserMapper;
import com.morning.morningshiro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
