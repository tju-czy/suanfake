package io.renren.modules.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.utils.ConvertUtils;
import io.renren.modules.course.dao.StudentDao;
import io.renren.modules.course.dto.CourseDTO;
import io.renren.modules.course.dto.StudentDTO;
import io.renren.modules.course.entity.StudentEntity;
import io.renren.modules.course.service.CourseService;
import io.renren.modules.course.service.StudentService;
import io.renren.modules.record.dto.*;
import io.renren.modules.record.service.*;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@Service
public class StudentServiceImpl extends CrudServiceImpl<StudentDao, StudentEntity, StudentDTO> implements StudentService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private RecordAfterclassService recordAfterclassService;
    @Autowired
    private RecordListenService recordListenService;
    @Autowired
    private RecordInteractionService recordInteractionService;
    @Autowired
    private RecordFlipService recordFlipService;
    @Autowired
    private RecordTeamwokService recordTeamwokService;
    @Autowired
    private RecordPoliticalService recordPoliticalService;
    @Autowired
    private RecordOtherService recordOtherService;
    @Autowired
    private CourseService courseService;

    @Override
    public QueryWrapper<StudentEntity> getWrapper(Map<String, Object> params){
        // CrudServiceImpl中的虚函数
        // 生成wrapper 供查询使用
        // 需要按照任何属性查 都可以传参到params中
        String id = (String)params.get("id");
        String studentId = params.get("studentId").toString();
        String studentName = (String)params.get("studentName");
        String classs = (String)params.get("classs");
        String courseId = (String)params.get("courseId");

        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(studentId), "student_id", studentId);
        wrapper.eq(StringUtils.isNotBlank(studentName), "student_name", studentName);
        wrapper.eq(StringUtils.isNotBlank(classs), "classs", classs);
        wrapper.eq(StringUtils.isNotBlank(courseId), "course_id", courseId);

        return wrapper;
    }

    @Override
    public List<StudentDTO> listByStuId(Map<String, Object> params){
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();

        wrapper.eq("student_id",params.get("student_id"));
        List<StudentEntity> entityList = baseDao.selectList(wrapper);

        return ConvertUtils.sourceToTarget(entityList, StudentDTO.class);
    }

//    @Override  // 改成下面的private方法
//    public void createUser(StudentDTO dto){
//        SysUserDTO userDTO = new SysUserDTO();
//        // 用户名和密码都是学号
//        userDTO.setUsername(dto.getStudentId().toString());
//        userDTO.setPassword(dto.getStudentId().toString());
//        sysUserService.save(userDTO);
//    }

    private void createUser(StudentDTO dto, Long roleId){
        SysUserDTO userDTO = new SysUserDTO();

        // 用户名和密码都是学号
        userDTO.setUsername(dto.getStudentId().toString());
        userDTO.setPassword(dto.getStudentId().toString());
        userDTO.setRealName(dto.getStudentName());
        userDTO.setStatus(1);

        // 为学生创建角色
        List<Long> roleList = new ArrayList<>();
        roleList.add(roleId);
        userDTO.setRoleIdList(roleList);

        sysUserService.save(userDTO);


    }

    @Override
    public void save(StudentDTO dto){
        super.save(dto);
        //获取角色“学生”对应的role id
        Long id4RoleStu = sysRoleService.getIdByName("学生");
        this.createUser(dto,id4RoleStu);
    }

    @Override
    public void saveBatch(Collection<StudentDTO> dtoList, int batchSize){
        super.saveBatch(dtoList,batchSize);
        //获取角色“学生”对应的role id
        Long id4RoleStu = sysRoleService.getIdByName("学生");

        for (StudentDTO dto:dtoList){
            this.createUser(dto,id4RoleStu);

        }
    }

    @Override
    public void updateRecord(Integer stuId, String recordType, int change){
        //change是1/-1
        UpdateWrapper<StudentEntity> wrapper = Wrappers.update();
        //修改语句
        wrapper.setSql(recordType+"= "+recordType+" + "+change);
        //条件
        wrapper.eq("student_id",stuId);
        //entity 里可以传入where条件（同上部分的eq）
        baseDao.update(null,wrapper);
    }

    @Override
    public void delete(Long[] ids){

        //同步删除user 用学号删除

        List<StudentEntity> entityList = baseDao.selectBatchIds(Arrays.asList(ids));
        String[] stuIds = new String[entityList.size()];
        for(int i = 0; i<entityList.size(); ++i){
            System.out.println("entityList stuId："+entityList.get(i).getStudentId());
            stuIds[i] = entityList.get(i).getStudentId().toString();
            System.out.println("xuehao :" + stuIds[i]);
        }
        // 删除学号对应的user(name)
        sysUserService.deleteByName(stuIds);

        //删除学生
        super.delete(ids);

        //TODO 删除学生对应的记录
    }

    @Override
    public List<RecordDTO> getRecords(Map<String, Object> params){
        // parms中有type、courseId、teacherId、stuId
        // 学生查看同班同学记录 ： type courseId
        // 老师查看本班同学记录 ： type teacherId
        // 学生查看自己的记录  ：  type stuId
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
        List<Integer> stuIdList = new ArrayList<>();
        String type = (String)params.get("type");
        if (StringUtils.isNotBlank((String)params.get("stuId"))){

            // 直接用学号查该学生记录
            stuIdList.add((Integer) params.get("stuId"));
        }
        else{
            // 获取同班级的学生
            String teacherId = (String)params.get("teacherId");
            System.out.println("teacher id: "+params.get("teacherId"));
            List<String> courseIdList = new ArrayList<>();
            if (StringUtils.isNotBlank(teacherId)){
                // 需要按照教师id查
                Map<String, Object> teId = new HashMap<>();
                teId.put("teacher_id",teacherId);
                List<CourseDTO> courseList = courseService.list(teId);
                for (CourseDTO d :courseList) {
                    courseIdList.add(d.getCourseId().toString());
                }
            }else if (StringUtils.isNotBlank((String)params.get("courseId"))){
                // 直接根据course_id查
                System.out.println("course id: "+params.get("courseId"));
                courseIdList.add((String)params.get("courseId"));
            }
            //查这些学生的学号
            for (String courseId: courseIdList) {
                System.out.println("courseId: "+courseId);
                wrapper.clear();
                wrapper.eq("course_id",courseId);
                List<StudentEntity> entityList = baseDao.selectList(wrapper);
                // 加入该班的所有学生学号
                for (StudentEntity entity:entityList
                ) {
                    System.out.println("studentId: "+ entity.getStudentId());
                    stuIdList.add(entity.getStudentId());
                }
            }
        }

        //用学号查记录

//        put(0,"listen_num");
//        put(1,"interaction_num");
//        put(2,"flip_num");
//        put(4,"teamwork_num");
        List<RecordDTO> result = new ArrayList<>();
        switch (type){
            case "3":
                List<RecordAfterclassDTO> list3 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list3.addAll(ConvertUtils.sourceToTarget(recordAfterclassService.getByStuId(stuId),RecordAfterclassDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list3,RecordDTO.class);
                break;
            case "0":
                List<RecordListenDTO> list0 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list0.addAll(ConvertUtils.sourceToTarget(recordListenService.getByStuId(stuId),RecordListenDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list0,RecordDTO.class);
                break;
            case "1":
                List<RecordInteractionDTO> list1 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list1.addAll(ConvertUtils.sourceToTarget(recordInteractionService.getByStuId(stuId),RecordInteractionDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list1,RecordDTO.class);
                break;
            case "2":
                List<RecordFlipDTO> list2 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list2.addAll(ConvertUtils.sourceToTarget(recordFlipService.getByStuId(stuId),RecordFlipDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list2,RecordDTO.class);
                break;
            case "4":
                List<RecordTeamwokDTO> list4 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list4.addAll(ConvertUtils.sourceToTarget(recordTeamwokService.getByStuId(stuId),RecordTeamwokDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list4,RecordDTO.class);
                break;
            case "5":
                List<RecordPoliticalDTO> list5 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list5.addAll(ConvertUtils.sourceToTarget(recordPoliticalService.getByStuId(stuId),RecordPoliticalDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list5,RecordDTO.class);
            case "6":
                List<RecordOtherDTO> list6 = new ArrayList<>();
                for (Integer stuId: stuIdList) {
                    list6.addAll(ConvertUtils.sourceToTarget(recordOtherService.getByStuId(stuId),RecordOtherDTO.class));
                }
                result = ConvertUtils.sourceToTarget(list6,RecordDTO.class);
        }

        Collections.sort(result,new RecordDTOComparator());
        return result;
    }
}