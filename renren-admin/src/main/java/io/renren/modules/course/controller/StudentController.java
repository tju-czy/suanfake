package io.renren.modules.course.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.course.dto.StudentDTO;
import io.renren.modules.course.excel.StudentExcel;
import io.renren.modules.course.service.StudentService;

import io.renren.modules.record.dto.RecordDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 学生信息
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@RestController
@RequestMapping("course/student")
@Api(tags="学生信息")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("course:student:page")
    public Result<PageData<StudentDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<StudentDTO> page = studentService.page(params);

        return new Result<PageData<StudentDTO>>().ok(page);
    }

    @GetMapping("records")
    @ApiOperation("记录分页")
    @RequiresPermissions("course:student:page")
    public Result<List<RecordDTO>> recordPage(@ApiIgnore @RequestParam Map<String, Object> params){
        // parms中有stuId、type、courseId
        List<RecordDTO> list = studentService.getRecords(params);


        return new Result<List<RecordDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("course:student:info")
    public Result<StudentDTO> get(@PathVariable("id") Long id){
        //PathVariable 接收url后直接跟参数

        System.out.println("访问到这个接口了");
        StudentDTO data = studentService.get(id);

        return new Result<StudentDTO>().ok(data);
    }

    @GetMapping(value = "/getByStuId")
    @ApiOperation("学号信息")
    @RequiresPermissions("course:student:info")
    public Result<StudentDTO> getByStuId(@RequestParam(value = "stuId") Integer stuId){
        System.out.println("xuehao: "+stuId);
        Map<String,Object> params = new HashMap<>();

        params.put("student_id",stuId);
        //非id的查询 只能调用CrudService的list方法
        //传入键值对作为查询的参数要求
        List<StudentDTO> list= studentService.listByStuId(params);
        StudentDTO data = list.get(0);
        return new Result<StudentDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("course:student:save")
    public Result save(@RequestBody StudentDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        studentService.save(dto);
        //创建user  已经在save中集成
        //studentService.createUser(dto);
        return new Result();
    }


    @PostMapping("/saveStuList")
    @ApiOperation("批量保存")
    @LogOperation("批量保存")
    @RequiresPermissions("course:course:save")
    public Result saveBatch(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println("访问了saveStuList");
        String name = file.getOriginalFilename();

        //检查文件格式
//        if(!name.substring(name.length()-4).equals(".xls")||
//                !name.substring(name.length()-5).equals(".xlsx")){
//            return new Result().error("格式错误");
//        }
//        System.out.println(file.isEmpty());
//        System.out.println("访问");
//        System.out.println(file.getOriginalFilename());
        //保存文件
          InputStream inputStream = file.getInputStream();
//        byte[] buffer = new byte[inputStream.available()];
//        inputStream.read(buffer);
//        File f = new File("d:\\student.xlsx");
//        OutputStream outputStream = new FileOutputStream(f);
//        outputStream.write(buffer);
//        outputStream.close();

        //处理文件生成list

        List list = ExcelUtils.importExcel(inputStream,StudentExcel.class);

        int batchSize = list.size();
//        System.out.println("controller :"+batchSize);
//        System.out.println(list.get(0).toString());
        //类型转换
        List<StudentDTO> dtoList = new ArrayList<>(batchSize);

        for(Object source: list){
            StudentDTO target = new StudentDTO();
            BeanUtils.copyProperties(source, target);
            dtoList.add(target);
        }
//        System.out.println("学号："+dtoList.get(0).getStudentId());
        //效验数据
        for (StudentDTO dto: dtoList) {

            ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
            //创建user
            //studentService.createUser(dto);
        }
        //批量存入数据
        studentService.saveBatch(dtoList,batchSize);
        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("course:student:update")
    public Result update(@RequestBody StudentDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        studentService.update(dto);

        return new Result();
    }
    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("course:student:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        studentService.delete(ids);  //还没有写删user

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("course:student:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<StudentDTO> list = studentService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, StudentExcel.class);
    }

}