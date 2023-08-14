package io.renren.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.RecordUtils;
import io.renren.modules.record.dao.RecordInteractionDao;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordInteractionDTO;
import io.renren.modules.record.entity.RecordAfterclassEntity;
import io.renren.modules.record.entity.RecordInteractionEntity;
import io.renren.modules.record.service.RecordInteractionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 课堂互动
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Service
public class RecordInteractionServiceImpl extends CrudServiceImpl<RecordInteractionDao, RecordInteractionEntity, RecordInteractionDTO> implements RecordInteractionService {

    @Override
    public QueryWrapper<RecordInteractionEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RecordInteractionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public void save(RecordInteractionDTO dto){
        // 保存该条记录
        super.save(dto);
        // 修改对应学生记录数
        RecordUtils.changeRecordNum(dto.getStudentId(),1,true);
    }

    @Override
    public void delete(Long[] ids){
        //先记录数减一
        for (Long id:ids){
            Integer studentId;
//            QueryWrapper<RecordAfterclassEntity> wrapper = new QueryWrapper<>();
//            wrapper.eq("id",id);
            RecordInteractionDTO dto = super.get(id);
            studentId = dto.getStudentId();
            RecordUtils.changeRecordNum(studentId,1,false);
        }

        super.delete(ids);
    }
    @Override
    //测试函数
    public List<RecordInteractionDTO> getByStuId(Integer stuId){
        QueryWrapper<RecordInteractionEntity> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("student_id",stuId);
        List<RecordInteractionEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList,RecordInteractionDTO.class);
    }

}