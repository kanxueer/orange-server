package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Activity;
import com.skbaby.orange.entity.Part;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PartMapper{

    /**
     * 通过活动ID查询排队
     * @param activityId ActivityID
     * @return Part
     */
    @Select({"select id,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where activityId = #{activityId}"})
    List<Part> queryByActivityId(int activityId);

    /**
     * 通过ID查询排队
     * @param id id
     * @return Part
     */
    @Select({"select id,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where id = #{id}"})
    Part queryById(int id);

    /**
     * 通过UserID查询排队
     * @param userId id
     * @return Part
     */
    @Select({"select id,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where id = #{userId}"})
    List<Part> queryByUserId(int userId);

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

    /**
     * 删除
     *
     * @param id id
     * @return 主键id
     */
    @Insert({"update part set state= 0 where id=#{id}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int deletePart(int id);

    /**
     * 更新Part对象
     *
     * @param part 对象
     * @return 主键id
     */
    @Insert({"update part set quantity=#{quantity} where id=#{id}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updatePart(Part part);
}
