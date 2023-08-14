package io.renren.modules.record.controller;

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
import io.renren.modules.record.dto.RecordTeacherDTO;
import io.renren.modules.record.excel.RecordTeacherExcel;
import io.renren.modules.record.service.RecordTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 教师贴
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-10-12
 */
@RestController
@RequestMapping("record/recordteacher")
@Api(tags="教师贴")
public class RecordTeacherController {
    @Autowired
    private RecordTeacherService recordTeacherService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("record:recordteacher:page")
    public Result<PageData<RecordTeacherDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<RecordTeacherDTO> page = recordTeacherService.page(params);

        return new Result<PageData<RecordTeacherDTO>>().ok(page);
    }
    @GetMapping("myTeacherRecords")
    @ApiOperation("教师记录")
    @RequiresPermissions("course:student:page")
    public Result<List<RecordTeacherDTO>> records(@ApiIgnore @RequestParam Map<String, Object> params){
        // parms中有stuId、type、courseId
        List<RecordTeacherDTO> list = recordTeacherService.getByCourseId(params);

        return new Result<List<RecordTeacherDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("record:recordteacher:info")
    public Result<RecordTeacherDTO> get(@PathVariable("id") Long id){
        RecordTeacherDTO data = recordTeacherService.get(id);

        return new Result<RecordTeacherDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("record:recordteacher:save")
    public Result save(@RequestBody RecordTeacherDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        //传入当前时间
        dto.setUploadTime(new Date());
        recordTeacherService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("record:recordteacher:update")
    public Result update(@RequestBody RecordTeacherDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        recordTeacherService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("record:recordteacher:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        recordTeacherService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("record:recordteacher:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<RecordTeacherDTO> list = recordTeacherService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, RecordTeacherExcel.class);
    }

}