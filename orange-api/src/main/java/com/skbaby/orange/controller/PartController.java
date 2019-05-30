package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.aspect.SecurityAspect;
import com.skbaby.orange.util.ResponseUtil;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.Part;
import com.skbaby.orange.enums.ErrorCode;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PartController {

    @Autowired
    private PartService partService;

    /**
     * 通过ActivityID查询
     *
     * @param activityId ActivityID
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/activity/{activityId}")
    public String getPartByActivityId(@PathVariable("activityId") Integer activityId) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByActivityId(activityId);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 根据ActivityID和userID查询
     *
     * @param activityId ActivityID
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/activity_with_user/{activityId}")
    public String getPartByActivityIdAndUserID(@PathVariable("activityId") Integer activityId) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByActivityIdAndUserId(activityId);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * PartID查询
     *
     * @param partId partId
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/{partId}")
    public String getPartByPartId(@PathVariable("partId") Integer partId) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        Part part = partService.queryPartById(partId);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 根据UserID查询
     *
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue")
    public String getPartByUserId() {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByUserId();
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 创建排队
     *
     * @param requestType requestType
     * @return int
     */
    @SecurityAspect
    @PostMapping(value = "/orange/queue")
    public String insertPart(@RequestBody RequestType requestType) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        Integer partId;
        try {
            partId = partService.insertPart(requestType);
            HashMap<String, Integer> data = new HashMap<>();
            data.put("partId", partId);
            responseType.setData(data);
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.INSERT_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.INSERT_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    /**
     * 删除Part
     * @param partId partId
     * @return responseType
     */
    @SecurityAspect
    @DeleteMapping(value = "/orange/queue/{partId}")
    public String deletePart(@PathVariable("partId") String partId){
        ResponseType responseType = ResponseUtil.defaultResponse();
        try {
            partService.deletePart(Integer.parseInt(partId));
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.DELETE_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.DELETE_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    /**
     * 更新
     * @param requestType requestType
     * @return id
     */
    @SecurityAspect
    @PutMapping(value = "/orange/queue")
    public String updatePart(@RequestBody RequestType requestType) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        partService.updatePart(requestType);
        return JSON.toJSONString(responseType);
    }
}
