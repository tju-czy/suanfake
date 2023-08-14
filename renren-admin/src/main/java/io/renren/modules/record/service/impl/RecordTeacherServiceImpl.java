package io.renren.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.course.service.CourseService;
import io.renren.modules.record.dao.RecordTeacherDao;
import io.renren.modules.record.dto.RecordListenDTO;
import io.renren.modules.record.dto.RecordTeacherDTO;
import io.renren.modules.record.entity.RecordListenEntity;
import io.renren.modules.record.entity.RecordTeacherEntity;
import io.renren.modules.record.service.RecordTeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师贴
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-10-12
 */
@Service
public class RecordTeacherServiceImpl extends CrudServiceImpl<RecordTeacherDao, RecordTeacherEntity, RecordTeacherDTO> implements RecordTeacherService {
    @Autowired
    private CourseService courseService;
    @Override
    public QueryWrapper<RecordTeacherEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RecordTeacherEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        String courseId = (String) params.get("courseId");
        if(StringUtils.isNotBlank(courseId)){ // 用于学生查自己班级的教师发布的教师贴
            Map<String, Object> coId = new HashMap<>();
            coId.put("course_id", courseId);
            String teacherId = courseService.list(coId).get(0).getTeacherId().toString();
            wrapper.eq(StringUtils.isNotBlank(teacherId),"student_id",teacherId);
        }
        String teacherId = (String)params.get("teacherId");
        if(StringUtils.isNotBlank(teacherId)){
            // 用于教师端查看自己的记录
            wrapper.eq(StringUtils.isNotBlank(teacherId),"student_id",teacherId);
        }

        return wrapper;
    }

    @Override
    public List<RecordTeacherDTO> getByCourseId(Map<String,Object> params){
        List<RecordTeacherEntity> entityList =  baseDao.selectList(getWrapper(params));
        return ConvertUtils.sourceToTarget(entityList,RecordTeacherDTO.class);
    }

//    // 用于学生查自己班级的教师发布的教师贴
//    @Override
//    public List<RecordTeacherDTO> getByStuIdAndCourseId(Map<String,Object> params){
//        List<RecordTeacherEntity> entityList =  baseDao.selectList(getWrapper(params));
//        return ConvertUtils.sourceToTarget(entityList,RecordTeacherDTO.class);
//    }

    public List<RecordTeacherDTO> getByStuId(Integer stuId){
        QueryWrapper<RecordTeacherEntity> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("student_id",stuId);
        List<RecordTeacherEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList,RecordTeacherDTO.class);
    }
}