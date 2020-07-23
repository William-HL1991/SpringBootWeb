package com.example.springbootweb.dao.mapper;


import com.example.springbootweb.dao.common.DSSelector;
import com.example.springbootweb.dao.common.MultipleDataSourceHelper;
import com.example.springbootweb.dao.module.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author william
 * @date 2020/7/23
 */
@Repository
public interface UserMapper {

    // 注解方式
    @Select("select * from user;")
    @DSSelector(MultipleDataSourceHelper.MASTER)
    List<User> selectAll();

    // xml方式查询
    @Select("select * from user;")
    @DSSelector(MultipleDataSourceHelper.SLAVE) // 指定从从库查询
    List<User> selectAllFromSalve();

    @Select("select password from user where username=#{username}")
    @DSSelector(MultipleDataSourceHelper.SLAVE) // 指定从从库查询
    String getPassword(String username);

    @Select("select role from user where username=#{username}")
    @DSSelector(MultipleDataSourceHelper.SLAVE) // 指定从从库查询
    String getRole(String username);

}
