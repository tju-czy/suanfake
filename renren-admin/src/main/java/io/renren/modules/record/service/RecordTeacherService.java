package io.renren.modules.record.service;

import io.renren.common.service.CrudService;
import io.renren.modules.record.dto.RecordTeacherDTO;
import io.renren.modules.record.entity.RecordTeacherEntity;

import java.util.List;
import java.util.Map;

/**
 * 教师贴
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-10-12
 */
public interface RecordTeacherService extends CrudService<RecordTeacherEntity, RecordTeacherDTO> {
    List<RecordTeacherDTO> getByStuId(Integer stuId);
    List<RecordTeacherDTO> getByCourseId(Map<String,Object> params);
}