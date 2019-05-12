package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.entity.Part;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PartMapper{

    /**
     * 通过活动ID查询排队
     * @param id ActivityID
     * @return Part
     */
    @Select({"select id,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where activityId = #{id}"})
    Part queryByActivityId(int id);

    /**
     * 通过ID查询排队
     * @param id id
     * @return Part
     */
    @Select({"select id,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where id = #{id}"})
    Part queryById(int id);

    /**
     * 新增排队对象
     *
     * @param part 对象
     * @return 主键id
     */
    @Insert({"insert into part(userId,activityId,quantity,shareType,shareId) " +
            "values(#{userId},#{activityId},#{quantity},#{shareType},#{shareId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertPart(Part part);
}
