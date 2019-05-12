package com.skbaby.orange.service;

import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public Activity queryActivityById(int id) {
        return activityMapper.queryById(id);
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
        int rows = activityMapper.updateActivity(activity);
        return activity.getId();
    }

    public void deleteActivity(int id) throws DaoException {
        int rows = activityMapper.deleteActivity(id);
        if (rows != 1){
            throw new DaoException();
        }
    }

    private Activity convertActivity(RequestType requestType) {
        Activity activity = new Activity();
        activity.setId(requestType.getActivityId());
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
