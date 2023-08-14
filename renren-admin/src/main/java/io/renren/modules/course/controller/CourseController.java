package io.renren.modules.course.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.course.dto.CourseDTO;
import io.renren.modules.course.excel.CourseExcel;
import io.renren.modules.course.service.CourseService;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 课程管理
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-23
 */
@RestController
@RequestMapping("course/course")
@Api(tags="课程管理")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("course:course:page")
    public Result<PageData<CourseDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CourseDTO> page = courseService.page(params);

        return new Result<PageData<CourseDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("course:course:info")
    public Result<CourseDTO> get(@PathVariable("id") Long id){
        CourseDTO data = courseService.get(id);

        return new Result<CourseDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("course:course:save")
    public Result save(@RequestBody CourseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        courseService.save(dto);

        return new Result();
    }

    @PostMapping("/saveCouList")
    @ApiOperation("批量保存")
    @LogOperation("批量保存")
    @RequiresPermissions("course:course:save")
    public Result saveBatch(@RequestBody MultipartFile file) throws Exception{
        String name = file.getOriginalFilename();
        if(!name.substring(name.length()-4).equals(".xls")||
                !name.substring(name.length()-4).equals(".xlsx")){
            return new Result().error("格式错误");
        }
        //处理文件生成list
        InputStream inputStream = file.getInputStream();
        List list = ExcelUtils.importExcel(inputStream,CourseDTO.class);

        int batchSize = list.size();
        //类型转换
        List<CourseDTO> dtoList = new ArrayList<>(batchSize);
        for(Object source: list){
            CourseDTO target = new CourseDTO();
            BeanUtils.copyProperties(source, target);
            dtoList.add(target);
        }
        //效验数据
        for (CourseDTO dto: dtoList) {

            ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        }
        //批量存入数据
        courseService.saveBatch(dtoList,batchSize);
        return new Result();
    }



    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("course:course:update")
    public Result update(@RequestBody CourseDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        courseService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("course:course:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        courseService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("course:course:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CourseDTO> list = courseService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CourseExcel.class);
    }

}