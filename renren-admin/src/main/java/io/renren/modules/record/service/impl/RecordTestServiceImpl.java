package io.renren.modules.record.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.renren.modules.record.service.RecordTestService;
import org.springframework.stereotype.Service;

//共有类RecordTestServiceImpl实现RecordTestService接口
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.RecordUtils;
import io.renren.modules.record.dao.RecordTestDao;
import io.renren.modules.record.dto.RecordTestDTO;
import io.renren.modules.record.entity.RecordTestEntity;

import java.util.List;
import java.util.Map;

@Service
public class RecordTestServiceImpl extends CrudServiceImpl<RecordTestDao, RecordTestEntity, RecordTestDTO> implements RecordTestService {
    //重写save方法
    @Override
    public void save(RecordTestDTO dto){
        // 保存该条记录
        super.save(dto);
        // 修改对应学生记录数
        RecordUtils.changeRecordNum(dto.getStudentId(),2,true);
    }
    //重写delete方法
    @Override
    public void delete(Long[] ids){
        //先记录数减一
        for (Long id:ids){
            Integer studentId;
            RecordTestDTO dto = super.get(id);
            studentId = dto.getStudentId();
            RecordUtils.changeRecordNum(studentId,2,false);
        }

        super.delete(ids);
    }
    //重写getByStuId方法
    @Override
    public List<RecordTestDTO> getByStuId(Integer stuId){
        //调用getByStuId方法，返回类型为List<RecordTestDTO>，参数为stuId
        return baseDao.getByStuId(stuId);
    }
    //重写getWrapper方法
    @Override
    public QueryWrapper<RecordTestEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<RecordTestEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }
}


