package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordFlipDTO;
import io.renren.modules.record.entity.RecordFlipEntity;

import java.util.List;

/**
 * 翻转课堂
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordFlipService extends CrudService<RecordFlipEntity, RecordFlipDTO> {
    List<RecordFlipDTO> getByStuId(Integer stuId);
}