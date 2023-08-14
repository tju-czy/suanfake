package io.renren.modules.record.service;
//接口RecordTestService继承CrudService<RecordTestEntity, RecordTestDTO>
import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordTestDTO;
import io.renren.modules.record.entity.RecordTestEntity;

import java.util.List;

public interface RecordTestService extends CrudService<RecordTestEntity, RecordTestDTO>{
    //声明getByStuId方法，返回类型为List<RecordTestDTO>，参数为Integer stuId
    List<RecordTestDTO> getByStuId(Integer stuId);
}
