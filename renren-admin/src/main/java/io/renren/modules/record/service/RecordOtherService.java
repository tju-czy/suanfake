package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordOtherDTO;
import io.renren.modules.record.entity.RecordOtherEntity;

import java.util.List;

/**
 * 其他
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordOtherService extends CrudService<RecordOtherEntity, RecordOtherDTO> {
    List<RecordOtherDTO> getByStuId(Integer stuId);
}