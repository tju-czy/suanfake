package io.renren.modules.record.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.intellij.lang.annotations.Pattern;

import java.util.Date;

/**
 * 课外学习
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Data
public class RecordAfterclassExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "学号")
    private Integer studentId;
    @Excel(name = "上课日期",format = "yyyy/MM/dd")
    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy/MM/dd")
    private Date sessionTime;
    @Excel(name = "提交日期",format = "yyyy/MM/dd HH:mm:ss")
    private Date uploadTime;
    @Excel(name = "描述")
    private String details;
    @Excel(name = "点赞")
    private Integer likes;
    @Excel(name = "类型")
    private Integer type;
    @Excel(name = "图片路径")
    private String fileUrl;

}