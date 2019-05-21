package com.skbaby.orange.service;

import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.mapper.ActivityMapper;
import com.skbaby.orange.util.SecurityThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public Activity queryActivityById(int id) {
        WeChatUser user = SecurityThreadLocal.get();
        return activityMapper.queryById(id, user.getId());
    }

    public List<Activity> queryActivityByUserId() {
        WeChatUser user = SecurityThreadLocal.get();
        return activityMapper.queryByUserId(user.getId());
    }

    public int insertActivity(RequestType requestType) throws DaoException {
        Activity activity = convertActivity(requestType);
        int rows = activityMapper.insertActivity(activity);
        if (rows != 1) {
            throw new DaoException();
        }
        return activity.getId();
    }

    public int updateActivity(RequestType requestType) {
        Activity activity = convertActivity(requestType);
        activityMapper.updateActivity(activity);
        return activity.getId();
    }

    public void deleteActivity(int id) throws DaoException {
        WeChatUser user = SecurityThreadLocal.get();
        int rows = activityMapper.deleteActivity(id, user.getId());
        if (rows != 1){
            throw new DaoException();
        }
    }

    private Activity convertActivity(RequestType requestType) {
        Activity activity = new Activity();
        WeChatUser user = SecurityThreadLocal.get();
        activity.setId(requestType.getActivityId());
        activity.setUserId(user.getId());
        activity.setTitle(requestType.getTitle());
        activity.setDescription(requestType.getDescription());
        activity.setStartTime(requestType.getStartTime());
        activity.setEndTime(requestType.getEndTime());
        activity.setQuantity(requestType.getActivityQuantity());
        activity.setLocation(requestType.getPosition());
        activity.setUnit(requestType.getUnit());
        return activity;
    }
}
