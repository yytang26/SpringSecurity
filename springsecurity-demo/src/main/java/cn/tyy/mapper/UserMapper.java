package cn.tyy.mapper;

import cn.tyy.entity.User;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PatchMapping;

/**
 * @Author:tyy
 * @version:1.0
 * @Date:2020/10/18 21:41
 */
public interface UserMapper {
    @Select("select * from users where usernam=#{username}")
    public User findByUser(@Param("username") String username);
}
