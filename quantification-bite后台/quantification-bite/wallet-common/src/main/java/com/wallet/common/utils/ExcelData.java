package com.wallet.common.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Author: 灵枢
 * Date: 2018/12/05
 * Time: 17:21
 * Description:读取Excel数据
 */
public class ExcelData {
	/**
     * 读取excel内容
     * <p>
     * 用户模式下：
     * 弊端：对于少量的数据可以，单数对于大量的数据，会造成内存占据过大，有时候会造成内存溢出
     * 建议修改成事件模式
     */
    public static List<String> redExcel(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()){
            throw new Exception("文件不存在!");
        }
        InputStream in = new FileInputStream(file);

        // 读取整个Excel
        XSSFWorkbook sheets = new XSSFWorkbook(in);
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = sheets.getSheetAt(0);
        ArrayList<String> list = new ArrayList<>();

        //默认第一行为标题行，i = 0
        XSSFRow titleRow = sheetAt.getRow(0);
        // 循环获取每一行数据
        for (int i = 0; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            // 读取每一格内容
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell titleCell = titleRow.getCell(index);
                XSSFCell cell = row.getCell(index);
                // cell.setCellType(XSSFCell.CELL_TYPE_STRING); 过期，使用下面替换
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                map.put(getString(titleCell), getString(cell));
                list.add(getString(cell));
            }
            if (map.isEmpty()) {
                continue;
            }
           
        }
        return list;
    }

    /**
     * 把单元格的内容转为字符串
     *
     * @param xssfCell 单元格
     * @return String
     */
    public static String getString(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else {
            return xssfCell.getStringCellValue();
        }
    
}

	//测试方法
	public static void main(String[] args){
		 try {
	            List<String> maps = redExcel("D:\\\\12345.xlsx");
	            maps.forEach(System.out::println);
	        }catch (Exception e){
	        	e.printStackTrace();
	            // System.out.println(e);
	        }
	
	}
}