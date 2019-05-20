package com.skbaby.orange.mapper;

import com.skbaby.orange.entity.WeChatUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WeChatUserMapper {

    /**
     * 通过openId查询用户
     *
     * @param openid openid
     * @return user
     */
    @Select({"select id,username,openid,profile,state from wechat_user where openid = #{openid} and state=1"})
    WeChatUser queryByOpenId(String openid);

    /**
     * 创建用户
     *
     * @param user 对象
     * @return 主键id
     */
    @Insert({"insert into wechat_user(openid, state) " +
            "values(#{openid},0)"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertWeChatUser(WeChatUser user);

    /**
     * 创建用户
     *
     * @param user 对象
     * @return 主键id
     */
    @Insert({"update wechat_user set username=#{username}, profile=#{profile}, state=#{state} where id=#{id} and openid=#{openid}"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int updateWeChatUser(WeChatUser user);
}
