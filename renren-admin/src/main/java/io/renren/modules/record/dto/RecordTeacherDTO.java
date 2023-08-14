package io.renren.modules.record.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 教师贴
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-10-12
 */
@Data
@ApiModel(value = "教师贴")
public class RecordTeacherDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "学号")
	private Integer studentId;

	@ApiModelProperty(value = "上课日期")
	private Date sessionTime;

	@ApiModelProperty(value = "提交日期")
	private Date uploadTime;

	@ApiModelProperty(value = "描述")
	private String details;

	@ApiModelProperty(value = "点赞")
	private Integer likes;

	@ApiModelProperty(value = "类型")
	private Integer type;


}