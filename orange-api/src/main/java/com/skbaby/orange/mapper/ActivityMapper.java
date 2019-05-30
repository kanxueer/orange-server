package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ActivityMapper {

    /**
     * 查询活动
     *
     * @param userId userId
     * @return Activity
     */
    @Select({"select activityId,userId,title,description,startTime,endTime,unit,location,quantity,state,dataCreate_LastTime,dataChange_LastTime from activity where userId= #{userId}"})
    @Results({
            @Result(property = "activityId", column = "activityId"),
            @Result(column = "partId", property = "parts",
                    many = @Many(
                            select = "com.skbaby.orange.mapper.PartMapper.queryByActivityId",
                            fetchType = FetchType.LAZY
                    )
            ),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userInfo", column = "userId",
                    one = @One(
                            select = "com.skbaby.orange.mapper.WeChatUserMapper.queryUserNameAndProfile",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    List<Activity> queryByUserId(int userId);

    /**
     * 查询活动
     *
     * @param activityId activity id
     * @return Activity
     */
    @Select({"select activityId,userId,title,description,startTime,endTime,unit,location,quantity,state,dataCreate_LastTime,dataChange_LastTime from activity where activityId = #{activityId}"})
    @Results({
            @Result(property = "activityId", column = "activityId"),
            @Result(column = "activityId", property = "parts",
                    many = @Many(
                            select = "com.skbaby.orange.mapper.PartMapper.queryByActivityId",
                            fetchType = FetchType.LAZY
                    )
            ),
            @Result(property = "userId", column = "userId"),
            @Result(property = "userInfo", column = "userId",
                    one = @One(
                            select = "com.skbaby.orange.mapper.WeChatUserMapper.queryUserNameAndProfile",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    Activity queryById(int activityId, int userId);

    /**
     * 新增Activity对象
     *
     * @param activity 对象
     * @return 主键id
     */
    @Insert({"insert into activity(userId,title,description,startTime,endTime,unit,location,quantity)" +
            "values(#{userId},#{title},#{description},#{startTime, jdbcType=TIMESTAMP},#{endTime, jdbcType=TIMESTAMP},#{unit},#{location},#{quantity})"})
    @Options(useGeneratedKeys = true, keyProperty = "activityId", keyColumn = "activityId")
    int insertActivity(Activity activity);

    /**
     * 更新Activity对象
     *
     * @param activity 对象
     * @return 主键id
     */
    @Update({"update activity set title=#{title},description=#{description},startTime=#{startTime, jdbcType=TIMESTAMP},endTime=#{endTime, jdbcType=TIMESTAMP}," +
            "unit=#{unit},location=#{location},quantity=#{quantity} where activityId=#{activityId} and userId=#{userId}"})
    void updateActivity(Activity activity);


    /**
     * 删除Activity对象
     *
     * @param activityId activityId
     * @return 主键id
     */
    @Update({"update activity set state= 0 where activityId=#{activityId}"})
    int deleteActivity(int activityId);

    /**
     * 关闭Activity活动
     *
     * @param activityId activityId
     * @return 主键id
     */
    @Update({"update activity set state = 2 where activityId=#{activityId}"})
    int closeActivity(int activityId);
}
