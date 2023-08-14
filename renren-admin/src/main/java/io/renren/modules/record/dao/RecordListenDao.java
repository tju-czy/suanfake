package io.renren.modules.record.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.record.entity.RecordListenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 认真听课
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@Mapper
public interface RecordListenDao extends BaseDao<RecordListenEntity> {
	
}