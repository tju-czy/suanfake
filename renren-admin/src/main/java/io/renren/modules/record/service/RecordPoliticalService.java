package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordPoliticalDTO;
import io.renren.modules.record.entity.RecordPoliticalEntity;

import java.util.List;

/**
 * 课程思政
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordPoliticalService extends CrudService<RecordPoliticalEntity, RecordPoliticalDTO> {
    List<RecordPoliticalDTO> getByStuId(Integer stuId);
}