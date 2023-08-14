package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordTeamwokDTO;
import io.renren.modules.record.entity.RecordTeamwokEntity;

import java.util.List;

/**
 * 小组作业
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
public interface RecordTeamwokService extends CrudService<RecordTeamwokEntity, RecordTeamwokDTO> {
    List<RecordTeamwokDTO> getByStuId(Integer stuId);

}