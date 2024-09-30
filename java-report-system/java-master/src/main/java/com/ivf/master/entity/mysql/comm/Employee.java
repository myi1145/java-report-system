package com.ivf.master.entity.mysql.comm;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 员工字典实体类，用于映射数据库表 COMM.EMPLOYEE_DICT
 */
@Data
@TableName("COMM.EMPLOYEE_DICT")  // 映射COMM.EMPLOYEE_DICT表
public class Employee {

    @Column(name = "USER_NAME")  // 映射 COMM.EMPLOYEE_DICT 表中的 USER_NAME 字段
    private String username;  // 用户名(工作人员字典主键)

    private String name;  // 姓名
    private String password;  // 密码(加密后)

    @Column(name = "DEPT_CODE")  // 映射 COMM.EMPLOYEE_DICT 表中的 DEPT_CODE 字段
    private String deptCode;  // 部门编码

    private int status;  // 用户状态

    @Column(name = "CREATE_TIME")  // 映射 COMM.EMPLOYEE_DICT 表中的 CREATE_TIME 字段
    private Date createTime;  // 创建时间

    @Column(name = "LAST_TIME")  // 映射 COMM.EMPLOYEE_DICT 表中的 LAST_TIME 字段
    private Date lastTime;  // 最后修改时间

    private List<Department> departmentList;
}
