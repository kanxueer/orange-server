package com.skbaby.orange.controller;

import com.alibaba.fastjson.JSON;
import com.skbaby.orange.dto.RequestType;
import com.skbaby.orange.dto.ResponseType;
import com.skbaby.orange.entity.Part;
import com.skbaby.orange.enums.ResponseCode;
import com.skbaby.orange.exception.DaoException;
import com.skbaby.orange.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class PartController {

    @Autowired
    private PartService partService;

    /**
     * 查询活动
     *
     * @param id id
     * @return object
     */
    @GetMapping(value = "/orange/queue/{id}")
    public String getPartActivityId(@PathVariable("id") Integer id) {
        ResponseType responseType = defaultResponse();
        Part part = partService.queryPartByActivityId(id);
        responseType.setData(part);
        return JSON.toJSONString(responseType);
    }

    /**
     * 创建活动
     *
     * @param requestType requestType
     * @return int
     */
    @PostMapping(value = "/orange/queue")
    public String insertActivity(@RequestBody RequestType requestType) {
        ResponseType responseType = defaultResponse();
        Integer partId;
        try {
            partId = partService.insertPart(requestType);
            HashMap<String, Integer> data = new HashMap<>();
            data.put("id", partId);
            responseType.setData(data);
        } catch (DaoException e) {
            responseType.setCode(ResponseCode.Insert_Error.getCode());
            responseType.setErr_msg(ResponseCode.Insert_Error.getMsg());
        }
        return JSON.toJSONString(responseType);
    }

    private ResponseType defaultResponse() {
        ResponseType responseType = new ResponseType();
        responseType.setCode(ResponseCode.SUCCESS.getCode());
        responseType.setErr_msg(ResponseCode.SUCCESS.getMsg());
        return responseType;
    }
}
