package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.WeChatUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WeChatUserMapper {

    /**
     * 通过openId查询用户
     *
     * @param openid openid
     * @return user
     */
    @Select({"select id,username,openid,token,profile,state,dataCreate_LastTime,dataChange_LastTime from wechat_user where openid = #{openid}  and state=1"})
    WeChatUser queryByOpenId(String openid);

    /**
     * 通过userId查询用户
     *
     * @param id id
     * @return user
     */
    @Select({"select id,username,openid,profile,state,dataCreate_LastTime,dataChange_LastTime from wechat_user where id = #{id} and state=1"})
    WeChatUser queryByUserId(int id);

    /**
     * 创建用户
     *
     * @param user 对象
     * @return 主键id
     */
    @Insert({"insert into wechat_user(openid, token, state) " +
            "values(#{openid},#{token},1)"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertWeChatUser(WeChatUser user);

    /**
     * 更新
     *
     * @param user 对象
     * @return 主键id
     */
    @Update({"update wechat_user set username=#{username}, profile=#{profile} where id=#{id}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updateWeChatUser(WeChatUser user);

    /**
     * 更新token
     * @param token
     * @param openid
     * @param newtoken
     */
    @Update({"update wechat_user set token=#{newtoken} where openid=#{openid} and token=#{token}"})
    int updateToken(String token, String openid, String newtoken);
}
