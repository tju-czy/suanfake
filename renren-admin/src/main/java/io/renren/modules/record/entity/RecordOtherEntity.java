package io.renren.modules.record.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 其他
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Data
@TableName("record_other")
public class RecordOtherEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 学号
     */
	private Integer studentId;
    /**
     * 上课日期
     */
	private Date sessionTime;
    /**
     * 提交日期
     */
	private Date uploadTime;
    /**
     * 描述
     */
	private String details;
    /**
     * 点赞
     */
	private Integer likes;
    /**
     * 类型
     */
	private Integer type;
    /**
     * 图片路径
     */
	private String fileUrl;
}