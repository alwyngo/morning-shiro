package com.morning.morningshiro.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.morning.morningshiro.dao.entity.UserEntity;
import com.morning.morningshiro.service.UserService;

import javax.annotation.Resource;
import java.util.List;

public class MyElasticJob implements SimpleJob {

    @Resource
    private UserService userService;

    @Override
    public void execute(ShardingContext context) {
        // 根据分片项执行任务
        System.out.println(context.toString());
        List<UserEntity> list = userService.list();
        switch (context.getShardingItem()) {
            case 0:
                break;
            case 1:
                // do something by sharding item 1
                break;
            case 2:
                // do something by sharding item 2
                break;
            // case n: ...
        }
    }
}