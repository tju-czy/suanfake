package io.renren.modules.course.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 课程管理
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Data
@ApiModel(value = "课程管理")
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "course_id")
	private Long courseId;

	@ApiModelProperty(value = "课程名")
	private String courseName;

	@ApiModelProperty(value = "教师id")
	private Long teacherId;

	@ApiModelProperty(value = "学期")
	private Integer term;

	@ApiModelProperty(value = "学分")
	private Integer credit;


}