package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.WeChatUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WeChatUserMapper {

    /**
     * 通过openId查询用户
     *
     * @param openId openId
     * @return user
     */
    @Select({"select userId,username,openId,token,profile,state,dataCreate_LastTime,dataChange_LastTime from wechat_user where openId = #{openId}  and state=1"})
    WeChatUser queryByOpenId(String openId);

    /**
     * 通过userId查询用户
     *
     * @param userId userId
     * @return user
     */
    @Select({"select userId,username,openId,profile,state,dataCreate_LastTime,dataChange_LastTime from wechat_user where userId = #{userId} and state=1"})
    WeChatUser queryByUserId(int userId);


    /**
     * 通过userId查询用户
     *
     * @param userId userId
     * @return user
     */
    @Select({"select username,profile from wechat_user where userId = #{userId} and state=1"})
    WeChatUser queryUserNameAndProfile(int userId);
    /**
     * 创建用户
     *
     * @param user 对象
     * @return 主键id
     */
    @Insert({"insert into wechat_user(openId, token, state) " +
            "values(#{openId},#{token},1)"})
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userId")
    int insertWeChatUser(WeChatUser user);

    /**
     * 更新
     *
     * @param user 对象
     * @return 主键id
     */
    @Update({"update wechat_user set username=#{username}, profile=#{profile} where userId=#{userId}"})
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "userId")
    void updateWeChatUser(WeChatUser user);

    /**
     * 更新token
     * @param token
     * @param openId
     * @param newtoken
     */
    @Update({"update wechat_user set token=#{newtoken} where openId=#{openId} and token=#{token}"})
    int updateToken(String token, String openId, String newtoken);
}
