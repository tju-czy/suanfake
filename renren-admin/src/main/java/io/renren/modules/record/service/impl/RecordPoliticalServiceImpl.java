package io.renren.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.RecordUtils;
import io.renren.modules.record.dao.RecordPoliticalDao;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordPoliticalDTO;
import io.renren.modules.record.entity.RecordAfterclassEntity;
import io.renren.modules.record.entity.RecordPoliticalEntity;
import io.renren.modules.record.service.RecordPoliticalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 课程思政
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Service
public class RecordPoliticalServiceImpl extends CrudServiceImpl<RecordPoliticalDao, RecordPoliticalEntity, RecordPoliticalDTO> implements RecordPoliticalService {

    @Override
    public QueryWrapper<RecordPoliticalEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RecordPoliticalEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
    @Override
    public void save(RecordPoliticalDTO dto){
        // 保存该条记录
        super.save(dto);
        // 修改对应学生记录数
        RecordUtils.changeRecordNum(dto.getStudentId(),5,true);
    }
    @Override
    public void delete(Long[] ids){
        //先记录数减一
        for (Long id:ids){
            Integer studentId;
//            QueryWrapper<RecordAfterclassEntity> wrapper = new QueryWrapper<>();
//            wrapper.eq("id",id);
            RecordPoliticalDTO dto = super.get(id);
            studentId = dto.getStudentId();
            RecordUtils.changeRecordNum(studentId,5,false);
        }

        super.delete(ids);
    }
    @Override
    //测试函数
    public List<RecordPoliticalDTO> getByStuId(Integer stuId){
        QueryWrapper<RecordPoliticalEntity> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("student_id",stuId);
        List<RecordPoliticalEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList,RecordPoliticalDTO.class);
    }
}