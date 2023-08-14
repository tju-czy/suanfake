package io.renren.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.RecordUtils;
import io.renren.modules.record.dao.RecordTeamwokDao;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordTeamwokDTO;
import io.renren.modules.record.entity.RecordAfterclassEntity;
import io.renren.modules.record.entity.RecordTeamwokEntity;
import io.renren.modules.record.service.RecordTeamwokService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 小组作业
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Service
public class RecordTeamwokServiceImpl extends CrudServiceImpl<RecordTeamwokDao, RecordTeamwokEntity, RecordTeamwokDTO> implements RecordTeamwokService {

    @Override
    public QueryWrapper<RecordTeamwokEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RecordTeamwokEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }

    @Override
    public void save(RecordTeamwokDTO dto){
        // 保存该条记录
        super.save(dto);
        // 修改对应学生记录数
        RecordUtils.changeRecordNum(dto.getStudentId(),4,true);
    }

    @Override
    public void delete(Long[] ids){
        //先记录数减一
        for (Long id:ids){
            Integer studentId;
//            QueryWrapper<RecordAfterclassEntity> wrapper = new QueryWrapper<>();
//            wrapper.eq("id",id);
            RecordTeamwokDTO dto = super.get(id);
            studentId = dto.getStudentId();
            RecordUtils.changeRecordNum(studentId,4,false);
        }

        super.delete(ids);
    }

    @Override
    //测试函数
    public List<RecordTeamwokDTO> getByStuId(Integer stuId){
        QueryWrapper<RecordTeamwokEntity> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("student_id",stuId);
        List<RecordTeamwokEntity> entityList = baseDao.selectList(wrapper);
        return ConvertUtils.sourceToTarget(entityList,RecordTeamwokDTO.class);
    }
}