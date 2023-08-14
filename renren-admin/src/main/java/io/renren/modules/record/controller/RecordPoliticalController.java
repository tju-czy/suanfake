package io.renren.modules.record.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.FileUtils;
import io.renren.common.utils.JsonUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.dto.RecordPoliticalDTO;
import io.renren.modules.record.excel.RecordPoliticalExcel;
import io.renren.modules.record.service.RecordPoliticalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 课程思政
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@RestController
@RequestMapping("record/recordpolitical")
@Api(tags="课程思政")
public class RecordPoliticalController {
    @Autowired
    private RecordPoliticalService recordPoliticalService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("record:recordpolitical:page")
    public Result<PageData<RecordPoliticalDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<RecordPoliticalDTO> page = recordPoliticalService.page(params);

        return new Result<PageData<RecordPoliticalDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("record:recordpolitical:info")
    public Result<RecordPoliticalDTO> get(@PathVariable("id") Long id){
        RecordPoliticalDTO data = recordPoliticalService.get(id);

        return new Result<RecordPoliticalDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("record:recordpolitical:save")
    public Result save(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "record") String dataJson) throws Exception{
        RecordPoliticalDTO dto = JsonUtils.parseObject(dataJson,RecordPoliticalDTO.class);

        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        //传入当前时间
        assert dto != null;
        dto.setUploadTime(new Date());
        //图片文件转本地url
        String url = FileUtils.saveRecordPicFile(file,dto.getStudentId().toString(),5);
        dto.setFileUrl(url);
        recordPoliticalService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("record:recordpolitical:update")
    public Result update(@RequestBody RecordPoliticalDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        recordPoliticalService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("record:recordpolitical:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        recordPoliticalService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("record:recordpolitical:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<RecordPoliticalDTO> list = recordPoliticalService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, RecordPoliticalExcel.class);
    }

}