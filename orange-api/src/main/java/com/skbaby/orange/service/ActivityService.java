package com.skbaby.orange.service;

import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.entity.Activity;
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

    public Activity queryActivityById(int activityId) {
        return activityMapper.queryById(activityId, SecurityThreadLocal.get().getUserId());
    }

    public List<Activity> queryActivityByUserId() {
        return activityMapper.queryByUserId(SecurityThreadLocal.get().getUserId());
    }

    public int insertActivity(RequestType requestType) throws DaoException {
        Activity activity = convertActivity(requestType);
        int rows = activityMapper.insertActivity(activity);
        if (rows != 1) {
            throw new DaoException();
        }
        return activity.getActivityId();
    }

    public void updateActivity(RequestType requestType) {
        Activity activity = convertActivity(requestType);
        activityMapper.updateActivity(activity);
    }

    public void deleteActivity(int activityId) throws DaoException {
        int rows = activityMapper.deleteActivity(activityId);
        if (rows != 1){
            throw new DaoException();
        }
    }

    public void closeActivity(int activityId) throws DaoException {
        int rows = activityMapper.closeActivity(activityId);
        if (rows != 1){
            throw new DaoException();
        }
    }

    private Activity convertActivity(RequestType requestType) {
        Activity activity = new Activity();
        activity.setActivityId(requestType.getActivityId());
        activity.setUserId(SecurityThreadLocal.get().getUserId());
        activity.setTitle(requestType.getTitle());
        activity.setDescription(requestType.getDescription());
        activity.setStartTime(requestType.getStartTime());
        activity.setEndTime(requestType.getEndTime());
        activity.setQuantity(requestType.getActivityQuantity());
        activity.setLocation(requestType.getLocation());
        activity.setUnit(requestType.getUnit());
        return activity;
    }
}
