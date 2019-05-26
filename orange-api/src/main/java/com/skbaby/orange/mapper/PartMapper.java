package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Part;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PartMapper{

    /**
     * 通过活动ID查询排队
     * @param activityId ActivityID
     * @return Part
     */
    @Select({"select id,userId,userName,profile,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where activityId = #{activityId}  and state=1"})
    List<Part> queryByActivityId(int activityId);

    /**
     * 通过ID查询排队
     * @param id id
     * @return Part
     */
    @Select({"select id,userId,userName,profile,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where id = #{id} and state=1"})
    Part queryById(int id);

    /**
     * 通过UserID查询排队
     * @param userId id
     * @return Part
     */
    @Select({"select id,userId,userName,profile,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where id = #{userId} and state=1"})
    List<Part> queryByUserId(int userId);

    /**
     * 新增排队对象
     *
     * @param part 对象
     * @return 主键id
     */
    @Insert({"insert into part(userId,activityId,userName,profile,quantity,shareType,shareId) " +
            "values(#{userId},#{activityId},#{userName},#{profile},#{quantity},#{shareType},#{shareId})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertPart(Part part);

    /**
     * 删除
     *
     * @param id id
     * @return 主键id
     */
    @Update({"update part set state= 0 where id=#{id}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int deletePart(int id);

    /**
     * 更新Part对象
     *
     * @param part 对象
     * @return 主键id
     */
    @Update({"update part set quantity=#{quantity} where id=#{id}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updatePart(Part part);
}
