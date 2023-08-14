package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.entity.RecordAfterclassEntity;

import java.util.List;

/**
 * 课外学习
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordAfterclassService extends CrudService<RecordAfterclassEntity, RecordAfterclassDTO> {
    List<RecordAfterclassDTO> getByStuId(Integer stuId);
}