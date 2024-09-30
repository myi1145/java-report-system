package com.ivf.master.entity.mysql.comm;


import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

/**
 * 部门字典实体类，用于映射数据库表 COMM.DEPARTMENT_DICT
 */
@Data
@TableName("COMM.DEPARTMENT_DICT")  // 映射COMM.DEPARTMENT_DICT表
public class Department {

    @Column(name = "DEPT_ID")
    private String deptId;

    @Column(name = "DEPT_CODE")   // 映射 COMM.EMPLOYEE_DICT 表中的 DEPT_CODE 字段
    private String deptCode;  //部门编码(部门字典联合主键)

    @Column(name = "DEPT_NAME")   // 映射 COMM.EMPLOYEE_DICT 表中的 DEPT_NAME 字段
    private String deptName;  //部门名称

    @Column(name = "HOSPITAL_CODE")   // 映射 COMM.EMPLOYEE_DICT 表中的 HOSPITAL_CODE 字段
    private String hospitalCode;  //院区编码(院区字典联合主键)

    @Column(name = "HOSPITAL_NAME")   // 映射 COMM.EMPLOYEE_DICT 表中的 HOSPITAL_NAME 字段
    private String hospitalName;  //院区名称

    private List<Employee> employeeList;
}
