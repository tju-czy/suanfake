package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordListenDTO;
import io.renren.modules.record.entity.RecordListenEntity;

import java.util.List;

/**
 * 认真听课
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordListenService extends CrudService<RecordListenEntity, RecordListenDTO> {
    List<RecordListenDTO> getByStuId(Integer stuId);
}