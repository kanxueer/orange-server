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
     * 查询PartID查询
     *
     * @param id id
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/{id}")
    public String getPartByPartId(@PathVariable("id") Integer id) {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByActivityId(id);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 根据UserID查询
     *
     * @return object
     */
    @SecurityAspect
    @GetMapping(value = "/orange/queue/")
    public String getPartByUserId() {
        ResponseType responseType = ResponseUtil.defaultResponse();
        List<Part> part = partService.queryPartByUserId();
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 创建活动
     *
     * @param requestType requestType
     * @return int
     */
    @SecurityAspect
    @PostMapping(value = "/orange/queue")
    public String insertActivity(@RequestBody RequestType requestType) {
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
}
