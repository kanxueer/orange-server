package com.skbaby.orange.service;

import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.entity.Part;
import com.skbaby.orange.entity.WeChatUser;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.mapper.PartMapper;
import com.skbaby.orange.util.SecurityThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService {

    @Autowired
    private PartMapper partMapper;

    public Part queryPartByActivityId(int activityId) {
        return partMapper.queryByActivityId(activityId);
    }

    public Part queryPartById(int activityId) {
        return partMapper.queryById(activityId);
    }

    public int insertPart(RequestType requestType) throws DaoException {
        Part part = convertPart(requestType);
        int rows = partMapper.insertPart(part);
        if (rows != 1) {
            throw new DaoException();
        }
        return part.getId();
    }

    private Part convertPart(RequestType requestType) {
        Part part = new Part();
        WeChatUser user = SecurityThreadLocal.get();
        part.setUserId(user.getId());
        part.setActivityId(requestType.getActivityId());
        part.setQuantity(requestType.getPartQuantity());
        part.setShareId(requestType.getShareId());
        part.setShareType(requestType.getShareType());
        return part;
    }
}
