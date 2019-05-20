package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface ActivityMapper {

    /**
     * 查询活动
     *
     * @param id activity id
     * @return Activity
     */
    @Select({"select id,title,description,startTime,endTime,unit,location,quantity,state,dataCreate_LastTime,dataChange_LastTime from activity where id = #{id} and userId= #{userId}"})
    @Results({
            @Result(column = "id", property = "parts",
                    many = @Many(
                            select = "com.skbaby.orange.mapper.PartMapper.queryByActivityId",
                            fetchType = FetchType.LAZY
                    )
            )
    })
    Activity queryById(int id, int userId);

    /**
     * 新增Activity对象
     *
     * @param activity 对象
     * @return 主键id
     */
    @Insert({"insert into activity(userId,title,description,startTime,endTime,unit,location,quantity)" +
            "values(#{userId},#{title},#{description},#{startTime, jdbcType=TIMESTAMP},#{endTime, jdbcType=TIMESTAMP},#{unit},#{location},#{quantity})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertActivity(Activity activity);

    /**
     * 更新Activity对象
     *
     * @param activity 对象
     * @return 主键id
     */
    @Insert({"update activity set title=#{title},description=#{description},startTime=#{startTime, jdbcType=TIMESTAMP},endTime=#{endTime, jdbcType=TIMESTAMP}," +
            "unit=#{unit},location=#{location},quantity=#{quantity} where id=#{id} and userId=#{userId}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updateActivity(Activity activity);


    /**
     * 删除Activity对象
     *
     * @param id id
     * @return 主键id
     */
    @Insert({"update activity set state= 0 where id=#{id} and userId=#{userId}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int deleteActivity(int id, int userId);

}
