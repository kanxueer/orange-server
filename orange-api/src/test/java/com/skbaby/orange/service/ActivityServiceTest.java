package com.skbaby.orange.service;

import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.mapper.ActivityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ActivityServiceTest {

    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void queryActivityById() {
        Activity activity = activityMapper.queryById(10000, 100);
        System.out.println(activity.toString());
    }

    @Test
    public void insertActivity(){
        Activity activity = new Activity();
        activity.setTitle("组团开车");
        activityMapper.insertActivity(activity);
        System.out.println(activity.getId());
    }
}