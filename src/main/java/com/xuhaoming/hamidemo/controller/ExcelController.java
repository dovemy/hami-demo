package com.xuhaoming.hamidemo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xuhaoming.hamidemo.pojo.ExcelImportRow;
import com.xuhaoming.hamidemo.service.DemoExcelHandler;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author xuhaoming
 * @date 2021/10/9 14:51
 */
@RestController
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private DemoExcelHandler demoEasyExcelHandler;

    @GetMapping("template")
    @ApiOperation("下载Excel模板")
    @ApiOperationSupport(order = 10)
    public void downloadExcelTemplate() throws IOException {
        demoEasyExcelHandler.downloadImportTemplate("导入模板", "excel/demo-template.xlsx", httpServletResponse);
    }


    @PostMapping("import")
    @ApiOperation("导入Excel")
    @ApiOperationSupport(order = 20)
    public void importExcel(@NotNull(message = "上传Excel不能为空") MultipartFile file) throws IOException {
        demoEasyExcelHandler.importExcel(file, "excel/demo-template.xlsx");
    }


    @GetMapping("export")
    @ApiOperation("导出Excel")
    @ApiOperationSupport(order = 30)
    public void exportExcel() throws IOException {
        List<ExcelImportRow> bodyRows = new ArrayList<>();
        bodyRows.add(new ExcelImportRow(1L, "标题1", "内容1"));
        bodyRows.add(new ExcelImportRow(2L, "标题2", "内容2"));
        bodyRows.add(new ExcelImportRow(3L, "标题3", "内容3"));
        demoEasyExcelHandler.exportExcel("测试导出", "excel/demo-export.xlsx", bodyRows, httpServletResponse);
    }

}
