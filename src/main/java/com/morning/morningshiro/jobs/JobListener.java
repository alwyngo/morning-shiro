package com.morning.morningshiro.jobs;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

public class JobListener implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        System.out.println(shardingContexts.toString());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        System.out.println(shardingContexts.toString());
    }
}
