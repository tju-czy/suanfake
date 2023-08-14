package io.renren.modules.course.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.course.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程管理
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Mapper
public interface CourseDao extends BaseDao<CourseEntity> {
	
}