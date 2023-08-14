package io.renren.modules.record.dto;

import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * 课外学习
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Data
public class RecordDTO {
    private Long id;
    private Integer studentId;
    private Date sessionTime;
    private Date uploadTime;
    private String details;
    private Integer likes;
    private Integer type;
    private String fileUrl = null;

}
