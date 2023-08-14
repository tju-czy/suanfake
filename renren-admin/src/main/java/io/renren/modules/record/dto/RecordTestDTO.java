package io.renren.modules.record.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

//注解Data

@Data
@ApiModel(value = "Test")
//一个类RecordTestDTO 有属性 id testValue time details，每个属性都有ApiModelProperty注解 有各属性的getter setter方法
public class RecordTestDTO {
    private Long id;
    private String testValue;
    private String time;
    private String details;

    public int getStudentId() {
        return 0;
    }
}



