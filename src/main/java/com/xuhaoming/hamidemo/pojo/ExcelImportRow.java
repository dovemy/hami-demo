package com.xuhaoming.hamidemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExcelImportRow {

    private Long id;

    private String title;

    private String content;

}
