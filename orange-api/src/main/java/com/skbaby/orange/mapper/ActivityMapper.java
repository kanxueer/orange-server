package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ActivityMapper {

    /**
     * 查询活动
     * @param id activity id
     * @return Activity
     */
    @Select({"select id,title,description,startTime,endTime,unit,location,quantity,dataCreate_LastTime,dataChange_LastTime from activity where id = #{id}"})
    Activity queryById(int id);

    /**
     * 新增Activity对象
     * @param activity 对象
     * @return 主键id
     */
    @Insert({"insert into activity(title,description,startTime,endTime,unit,location,quantity)" +
                    "values(#{title},#{description},#{startTime, jdbcType=TIMESTAMP},#{endTime, jdbcType=TIMESTAMP},#{unit},#{location},#{quantity})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
    int insertActivity(Activity activity);
}
