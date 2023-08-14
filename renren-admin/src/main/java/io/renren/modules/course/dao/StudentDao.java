package io.renren.modules.course.dao;

import io.renren.common.dao.BaseDao;
import io.renren.modules.course.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Mapper
public interface StudentDao extends BaseDao<StudentEntity> {
    //@Mapper注解自动实现基本的增删改查
}