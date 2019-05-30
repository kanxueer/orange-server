package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Part;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface PartMapper{

    /**
     * 通过活动ID查询排队
     * @param activityId ActivityID
     * @return Part
     */
    @Select({"select partId,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where activityId = #{activityId} and state=1"})
    @Results({
            @Result(property = "partId", column = "partId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userInfo", column = "userId",
                    one = @One(
                            select = "com.skbaby.orange.mapper.WeChatUserMapper.queryUserNameAndProfile",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Part> queryByActivityId(int activityId);


    /**
     * 通过活动ID和用户ID查询
     * @param activityId ActivityID
     * @return Part
     */
    @Select({"select partId,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where activityId = #{activityId} and userId = #{userId} and state=1"})
    @Results({
            @Result(property = "partId", column = "partId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userInfo", column = "userId",
                    one = @One(
                            select = "com.skbaby.orange.mapper.WeChatUserMapper.queryUserNameAndProfile",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Part> queryByActivityIdAndUserId(int activityId, int userId);

    /**
     * 通过ID查询排队
     * @param partId partId
     * @return Part
     */
    @Select({"select partId,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where partId = #{partId} and state=1"})
    @Results({
            @Result(property = "partId", column = "partId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userInfo", column = "userId",
                    one = @One(
                            select = "com.skbaby.orange.mapper.WeChatUserMapper.queryUserNameAndProfile",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    Part queryById(int partId);

    /**
     * 通过UserID查询排队
     * @param userId id
     * @return Part
     */
    @Select({"select partId,userId,activityId,quantity,shareType,shareId,state,dataCreate_LastTime,dataChange_LastTime from part where userId = #{userId} and state=1"})
    @Results({
            @Result(property = "partId", column = "partId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userInfo", column = "userId",
                    one = @One(
                            select = "com.skbaby.orange.mapper.WeChatUserMapper.queryUserNameAndProfile",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Part> queryByUserId(int userId);

    /**
     * 新增排队对象
     *
     * @param part 对象
     * @return 主键id
     */
    @Insert({"insert into part(userId,activityId,username,profile,quantity,shareType,shareId) " +
            "values(#{userId},#{activityId},#{username},#{profile},#{quantity},#{shareType},#{shareId})"})
    @Options(useGeneratedKeys = true, keyProperty = "partId", keyColumn = "partId")
    int insertPart(Part part);

    /**
     * 删除
     *
     * @param partId partId
     * @return 主键id
     */
    @Delete({"update part set state= 0 where id=#{partId}"})
    @Options(useGeneratedKeys = true, keyProperty = "partId", keyColumn = "partId")
    int deletePart(int partId);

    /**
     * 更新Part对象
     *
     * @param part 对象
     * @return 主键id
     */
    @Update({"update part set quantity=#{quantity} where partId=#{partId}"})
    @Options(useGeneratedKeys = true, keyProperty = "partId", keyColumn = "partId")
    void updatePart(Part part);
}
