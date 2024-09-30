package com.ivf.master.mapper.login;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 登录模块的MyBatis Mapper接口。
 * 用于定义与用户登录相关的数据库操作。
 */
@Mapper
public interface LoginMapper {

    /**
     * 检查用户是否存在于数据库中。
     * 此方法使用用户名作为查询条件，在数据库表COMM.EMPLOYEE_DICT中查询用户是否存在。
     * 如果用户存在，返回的整数大于0；否则返回0。
     *
     * @param username 用户名，用于在数据库中查找用户。
     * @return 用户存在时返回的整数大于0，不存在时返回0。
     */
    int SelectUser(String username);

    /**
     * 获取用户密码和姓名信息。
     * 根据用户名，在数据库表COMM.EMPLOYEE_DICT中查询对应的用户密码和姓名信息。
     * 返回的Map中，键 "PASSWORD" 对应用户的密码，键 "NAME" 对应用户的姓名。
     * 该方法用于登录过程中验证用户密码以及获取用户姓名等信息。
     *
     * @param username 用户名，用于在数据库中查找对应的密码和姓名信息。
     * @return 包含密码和姓名信息的Map，键为数据库字段名。
     */
    Map<String, Object> GetPassword(String username);

}
