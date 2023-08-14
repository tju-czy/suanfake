package io.renren.modules.record.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.exception.ErrorCode;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.utils.FileUtils;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.record.dto.RecordAfterclassDTO;
import io.renren.modules.record.excel.RecordAfterclassExcel;
import io.renren.modules.record.service.RecordAfterclassService;
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
import io.renren.common.utils.JsonUtils;
import com.alibaba.fastjson.JSON;

/**
 * 课外学习
 *
 * @author zxh zhengxh0621@tju.edu.cn
 * @since 1.0.0 2022-09-26
 */
@RestController
@RequestMapping("record/recordafterclass")
@Api(tags="课外学习")
@CrossOrigin
public class RecordAfterclassController {
    @Autowired
    private RecordAfterclassService recordAfterclassService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("record:recordafterclass:page")
    public Result<PageData<RecordAfterclassDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<RecordAfterclassDTO> page = recordAfterclassService.page(params);
        return new Result<PageData<RecordAfterclassDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("record:recordafterclass:info")
    public Result<RecordAfterclassDTO> get(@PathVariable("id") Long id){
        RecordAfterclassDTO data = recordAfterclassService.get(id);

        return new Result<RecordAfterclassDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("record:recordafterclass:save")
    public Result save(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "record") String dataJson) throws Exception{
        if (file.isEmpty()) {
            return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }
        System.out.println(dataJson);
        //String jsonStr = JSON.parse(dataJson).toString();
        //System.out.println(jsonStr);
        //RecordAfterclassDTO dto = JSON.parseObject(dataJson,RecordAfterclassDTO.class);
        RecordAfterclassDTO dto = JsonUtils.parseObject(dataJson,RecordAfterclassDTO.class);
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        //传入当前时间
        dto.setUploadTime(new Date());

        //图片文件转本地url
        System.out.println(file.getOriginalFilename());
        System.out.println("studentId"+dto.getStudentId());
        String url = FileUtils.saveRecordPicFile(file,dto.getStudentId().toString(),3);
        dto.setFileUrl(url);
        System.out.println(url);
        recordAfterclassService.save(dto);


        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("record:recordafterclass:update")
    public Result update(@RequestBody RecordAfterclassDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        recordAfterclassService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("record:recordafterclass:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        recordAfterclassService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("record:recordafterclass:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<RecordAfterclassDTO> list = recordAfterclassService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, RecordAfterclassExcel.class);
    }
}