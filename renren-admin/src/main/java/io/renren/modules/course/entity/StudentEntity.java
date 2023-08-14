package io.renren.modules.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Data
@TableName("student")
public class StudentEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 学号
     */
	private Integer studentId;
    /**
     * 姓名
     */
	private String studentName;
    /**
     * 班级
     */
	private String classs;
    /**
     * 学院
     */
	private String school;
    /**
     * 专业
     */
	private String major;
    /**
     * 密码
     */
	private String password;
    /**
     * 认真听课
     */
	private Integer listenNum;
    /**
     * 课堂互动
     */
	private Integer interactionNum;
    /**
     * 翻转课堂
     */
	private Integer flipNum;
    /**
     * 课外学习
     */
	private Integer afterClassNum;
    /**
     * 小组作业
     */
	private Integer teamworkNum;
    /**
     * 课程思政
     */
	private Integer politicalNum;
    /**
     * 其他数
     */
	private Integer otherNum;
    /**
     * 教师评价
     */
	private String judge;
    /**
     * 课程id
     */
	private Long courseId;
}