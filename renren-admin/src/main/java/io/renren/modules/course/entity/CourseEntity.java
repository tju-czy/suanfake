package io.renren.modules.course.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 课程管理
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Data
@TableName("course")
public class CourseEntity {

    /**
     * id
     */
	private Long id;
    /**
     * course_id
     */
	private Long courseId;
    /**
     * 课程名
     */
	private String courseName;
    /**
     * 教师id
     */
	private Long teacherId;
    /**
     * 学期
     */
	private Integer term;
    /**
     * 学分
     */
	private Integer credit;
}