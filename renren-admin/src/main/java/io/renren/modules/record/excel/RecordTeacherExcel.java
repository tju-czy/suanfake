package io.renren.modules.record.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 教师贴
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-10-12
 */
@Data
public class RecordTeacherExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "学号")
    private Integer studentId;
    @Excel(name = "上课日期")
    private Date sessionTime;
    @Excel(name = "提交日期")
    private Date uploadTime;
    @Excel(name = "描述")
    private String details;
    @Excel(name = "点赞")
    private Integer likes;
    @Excel(name = "类型")
    private Integer type;

}