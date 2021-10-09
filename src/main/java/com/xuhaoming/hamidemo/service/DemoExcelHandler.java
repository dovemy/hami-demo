package com.xuhaoming.hamidemo.service;

import com.xuhaoming.hamidemo.pojo.ExcelImportRow;
import io.github.dovemy.hami.web.util.easyexcel.AbstractExcelHandler;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义excel导入导出处理器——按需重写方法即可
 *
 * @author xuhaoming
 * @date 2021/9/2 22:23
 */
@Service
public class DemoExcelHandler extends AbstractExcelHandler<ExcelImportRow, ExcelImportRow> {

    @Override
    protected void handleImportBodyRows(List<ExcelImportRow> bodyRows) {
        for (ExcelImportRow bodyRow : bodyRows) {
            System.out.println(bodyRow.toString());
        }
    }


}
