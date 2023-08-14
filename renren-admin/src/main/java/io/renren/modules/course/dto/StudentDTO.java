package io.renren.modules.course.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Data
@ApiModel(value = "学生信息")
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "学号")
	private Integer studentId;

	@ApiModelProperty(value = "姓名")
	private String studentName;

	@ApiModelProperty(value = "班级")
	private String classs;

	@ApiModelProperty(value = "学院")
	private String school;

	@ApiModelProperty(value = "专业")
	private String major;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "认真听课")
	private Integer listenNum;

	@ApiModelProperty(value = "课堂互动")
	private Integer interactionNum;

	@ApiModelProperty(value = "翻转课堂")
	private Integer flipNum;

	@ApiModelProperty(value = "课外学习")
	private Integer afterClassNum;

	@ApiModelProperty(value = "小组作业")
	private Integer teamworkNum;

	@ApiModelProperty(value = "课程思政")
	private Integer politicalNum;

	@ApiModelProperty(value = "其他数")
	private Integer otherNum;

	@ApiModelProperty(value = "教师评价")
	private String judge;

	@ApiModelProperty(value = "课程id")
	private Long courseId;


}