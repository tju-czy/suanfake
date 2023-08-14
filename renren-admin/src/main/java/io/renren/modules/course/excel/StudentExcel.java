package io.renren.modules.course.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Data
public class StudentExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "学号")
    private Integer studentId;
    @Excel(name = "姓名")
    private String studentName;
    @Excel(name = "班级")
    private String classs;
    @Excel(name = "学院")
    private String school;
    @Excel(name = "专业")
    private String major;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "认真听课")
    private Integer listenNum;
    @Excel(name = "课堂互动")
    private Integer interactionNum;
    @Excel(name = "翻转课堂")
    private Integer flipNum;
    @Excel(name = "课外学习")
    private Integer afterClassNum;
    @Excel(name = "小组作业")
    private Integer teamworkNum;
    @Excel(name = "课程思政")
    private Integer politicalNum;
    @Excel(name = "其他数")
    private Integer otherNum;
    @Excel(name = "教师评价")
    private String judge;
    @Excel(name = "课程id")
    private Long courseId;

}