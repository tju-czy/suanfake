package io.renren.modules.course.service;

import io.renren.common.service.CrudService;
import io.renren.modules.course.dto.StudentDTO;
import io.renren.modules.course.entity.StudentEntity;
import io.renren.modules.record.dto.RecordDTO;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
public interface StudentService extends CrudService<StudentEntity, StudentDTO> {

    List<StudentDTO> listByStuId(Map<String, Object> params);

//    void createUser(StudentDTO dto);

    void updateRecord(Integer stuId, String recordType, int change);

    List<RecordDTO> getRecords(Map<String, Object> params);
}