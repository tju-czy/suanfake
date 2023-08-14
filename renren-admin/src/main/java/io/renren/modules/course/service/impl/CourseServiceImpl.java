package io.renren.modules.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.modules.course.dao.CourseDao;
import io.renren.modules.course.dto.CourseDTO;
import io.renren.modules.course.entity.CourseEntity;
import io.renren.modules.course.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 课程管理
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Service
public class CourseServiceImpl extends CrudServiceImpl<CourseDao, CourseEntity, CourseDTO> implements CourseService {

    @Override
    public QueryWrapper<CourseEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CourseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        return wrapper;
    }

//    public void saveBatch(MultipartFile file){
//        int batchSize = 0;
//        List<CourseDTO> dtoList = new ArrayList<>();
//        //解析文件
//
//        //调用上层方法
//        super.saveBatch(dtoList,batchSize);
//    }
}