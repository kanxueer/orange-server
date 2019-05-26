package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.aspect.SecurityAspect;
import com.skbaby.orange.controller.util.ResponseUtil;
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
     * 查询ActivityID查询
     *
     * @param id ActivityID
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/activity/{id}")
    public String getPartByActivityId(@PathVariable("id") Integer id) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByActivityId(id);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 根据ActivityID和userID查询
     *
     * @param id ActivityID
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/activity_with_user/{id}")
    public String getPartByActivityIdAndUserID(@PathVariable("id") Integer id) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByActivityIdAndUserId(id);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * PartID查询
     *
     * @param id id
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/{id}")
    public String getPartByPartId(@PathVariable("id") Integer id) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        Part part = partService.queryPartById(id);
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
            data.put("id", partId);
            responseType.setData(data);
        } catch (DaoException e) {
            responseType.setCode(ErrorCode.INSERT_ERROR.getCode());
            responseType.setErr_msg(ErrorCode.INSERT_ERROR.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    /**
     * 删除Part
     * @param id id
     * @return responseType
     */
    @SecurityAspect
    @DeleteMapping(value = "/orange/queue/{id}")
    public String deletePart(@PathVariable("id") Integer id){
        ResponseType responseType = ResponseUtil.defaultResponse();
        try {
            partService.deletePart(id);
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

        int part = partService.updatePart(requestType);
        HashMap<String, Integer> data = new HashMap<>();
        data.put("id", part);
        responseType.setData(data);

        return JSON.toJSONString(responseType);
    }
}
