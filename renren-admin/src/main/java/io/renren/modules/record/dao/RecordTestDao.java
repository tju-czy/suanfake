package io.renren.modules.record.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.record.dto.RecordTestDTO;
import io.renren.modules.record.entity.RecordTestEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordTestDao extends BaseMapper<RecordTestEntity> {
    List<RecordTestDTO> getByStuId(Integer stuId);
}
