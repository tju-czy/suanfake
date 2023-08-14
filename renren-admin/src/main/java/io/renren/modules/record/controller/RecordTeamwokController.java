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
import io.renren.modules.record.dto.RecordTeamwokDTO;
import io.renren.modules.record.excel.RecordTeamwokExcel;
import io.renren.modules.record.service.RecordTeamwokService;
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
 * 小组作业
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@RestController
@RequestMapping("record/recordteamwok")
@Api(tags="小组作业")
public class RecordTeamwokController {
    @Autowired
    private RecordTeamwokService recordTeamwokService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("record:recordteamwok:page")
    public Result<PageData<RecordTeamwokDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<RecordTeamwokDTO> page = recordTeamwokService.page(params);

        return new Result<PageData<RecordTeamwokDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("record:recordteamwok:info")
    public Result<RecordTeamwokDTO> get(@PathVariable("id") Long id){
        RecordTeamwokDTO data = recordTeamwokService.get(id);

        return new Result<RecordTeamwokDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("record:recordteamwok:save")
    public Result save(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "record") String dataJson) throws Exception{
       RecordTeamwokDTO dto = JsonUtils.parseObject(dataJson,RecordTeamwokDTO.class);
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        //传入当前时间
        dto.setUploadTime(new Date());

        //图片文件转本地url
        String url = FileUtils.saveRecordPicFile(file,dto.getStudentId().toString(),4);
        dto.setFileUrl(url);
        recordTeamwokService.save(dto);


        return new Result();
    }


    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("record:recordteamwok:update")
    public Result update(@RequestBody RecordTeamwokDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        recordTeamwokService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("record:recordteamwok:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        recordTeamwokService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("record:recordteamwok:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<RecordTeamwokDTO> list = recordTeamwokService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, RecordTeamwokExcel.class);
    }

}