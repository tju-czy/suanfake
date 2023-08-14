package io.renren.modules.record.controller;

import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.modules.record.dto.RecordTestDTO;
import io.renren.modules.record.service.RecordTestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@RequestMapping("record/recordTest")
@Api(tags="测试")
@CrossOrigin
public class RecordTestController {
    @Autowired
    private RecordTestService recordTestService;

    //公有方法page 返回类型为Result<PageData<RecordTestDTO>>，参数为Map<String, Object> params 有注解GetMapping("page")\ApiOperation("分页")\ApiImplicitParams
    @ApiIgnore
    @RequestMapping("/page")
    public Result<PageData<RecordTestDTO>> page(@RequestParam Map<String, Object> params){
        PageData<RecordTestDTO> page = recordTestService.page(params);
        return new Result<PageData<RecordTestDTO>>().ok(page);
    }
}
