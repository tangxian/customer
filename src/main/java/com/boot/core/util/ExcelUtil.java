package com.boot.core.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;

/**
 * HSSFWorkbook     excel的文档对象
 * HSSFSheet            excel的表单
 * HSSFRow               excel的行
 * HSSFCell                excel的格子单元
 * HSSFFont               excel字体
 * HSSFCellStyle         cell样式
 */
public class ExcelUtil {

    /**
     * 导出Excel
     *
     * @param sheetName sheet名称
     * @param title     标题
     * @param mapList   内容
     * @param wb        HSSFWorkbook对象
     * @param queryList 查询条件
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, List<LinkedHashMap<String, Object>> mapList, HSSFWorkbook wb, List queryList) {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        // 第四步，在sheet中添加表头第1行,注意老版本poi对Excel的行数列数有限制 开始行数
        HSSFRow row;
        //是否有查询条件
        if (queryList.size() > 0) {
            row = sheet.createRow(0);//有查询条件 将查询条件放第一行
            HSSFCell queryCell = null;
            queryList.add(0, "查询条件: ");
            for (int i = 0; i < queryList.size(); i++) {
                queryCell = row.createCell(i);
                queryCell.setCellValue(queryList.get(i) + "");
                queryCell.setCellStyle(style);
            }
            row = sheet.createRow(1);//条件写入完毕 另开一行
        } else {//没有查询条件 第0行开始
            row = sheet.createRow(0);
        }

        //声明列对象
        HSSFCell cell = null;
        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        //创建内容
        for (int i = 0; i < mapList.size(); i++) {
            if (queryList.size() > 0) {
                row = sheet.createRow(i + 2);//有查询条件的开始行数
            } else {
                row = sheet.createRow(i + 1);//没有查询添加的开始行数
            }
            //设置列宽
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 17 / 10);
            LinkedHashMap<String, Object> map = mapList.get(i);
            Object[] objects = map.values().toArray();
            for (int j = 0; j < objects.length; j++) {
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(objects[j] + "");
                cell.setCellStyle(style);
            }
        }

        return wb;
    }
}
