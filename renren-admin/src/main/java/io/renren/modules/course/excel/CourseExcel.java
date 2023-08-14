package io.renren.modules.course.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 课程管理
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Data
public class CourseExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "course_id")
    private Long courseId;
    @Excel(name = "课程名")
    private String courseName;
    @Excel(name = "教师id")
    private Long teacherId;
    @Excel(name = "学期")
    private Integer term;
    @Excel(name = "学分")
    private Integer credit;

}