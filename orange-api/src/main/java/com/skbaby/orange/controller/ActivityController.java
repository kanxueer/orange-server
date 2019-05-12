package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping(value="/orange/activity/{id}")
    public String getActivity(@PathVariable("id") Integer id){
        ResponseType responseType = defaultResponse();
        Activity activity = activityService.queryActivityById(id);
        responseType.setData(activity);
        return JSON.toJSONString(responseType);
    }

    @PostMapping(value="/orange/activity")
    public String insertActivity(@RequestBody RequestType requestType){
        ResponseType responseType = defaultResponse();
        Integer activityId;
        try {
            activityId = activityService.insertActivity(requestType);
            HashMap<String,Integer> data = new HashMap<>();
            data.put("id", activityId);
            responseType.setData(data);
        } catch (DaoException e) {
            responseType.setCode(1);
            responseType.setErr_msg(e.getMessage());
        }
        return JSON.toJSONString(responseType);
    }

    private ResponseType defaultResponse(){
        ResponseType responseType = new ResponseType();
        responseType.setCode(0);
        responseType.setErr_msg("");
        return responseType;
    }
}
