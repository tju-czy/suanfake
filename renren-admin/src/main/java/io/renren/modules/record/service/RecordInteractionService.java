package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordInteractionDTO;
import io.renren.modules.record.entity.RecordInteractionEntity;

import java.util.List;

/**
 * 课堂互动
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordInteractionService extends CrudService<RecordInteractionEntity, RecordInteractionDTO> {
    List<RecordInteractionDTO> getByStuId(Integer stuId);
}