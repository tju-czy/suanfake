package io.renren.modules.record.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.record.entity.RecordTeacherEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教师贴
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-10-12
 */
@Mapper
public interface RecordTeacherDao extends BaseDao<RecordTeacherEntity> {
	
}