/* 
 * @(#)FileUtils.java    Created on 2010-9-27
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package bjxg.test.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormats;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

import net.zdsoft.keel.util.StringUtils;

/**
 * @author chengcy
 * @version $Revision: 1.0 $, $Date: 2010-9-27 上午10:45:44 $
 */
public class FileUtils {
    /**
     * Logger for this class
     */
    private static final Logger log = Logger.getLogger(FileUtils.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FileUtils u = new FileUtils();
        String studentId = "学生编号";
        String studentName = "学生姓名";
        String[] fieldTitles = { studentId, studentName };
        Map<String, List<Map>> map = new HashMap<String, List<Map>>();
        List<Map> list = new ArrayList<Map>();
        Map tm = new HashMap();
        tm.put(studentId, "1");
        tm.put(studentName, "2");
        Map tm2 = new HashMap();
        tm2.put(studentId, "12");
        tm2.put(studentName, "22");
        list.add(tm);
        list.add(tm2);
        map.put("3", list);
        u.exportXLSFile("a.xls", fieldTitles, map);
    }

    /**
     * 将记录信息导出为xls格式文件. (未使用reflection来实现的方法)
     * 
     * @param fileName
     *            导出文件的名称
     * @param fieldTitles
     *            工作表的字段标题数组(首行)
     * @param sheetName2RecordListMap
     *            记录集map, 其中key为工作表名称, value为一个list(内含字段标题-字段值的map).
     */
    protected void exportXLSFile(String fileName, String[] fieldTitles, Map<String, List<Map>> sheetName2RecordListMap) {

        WritableWorkbook workbook = null;
        File file = new File(fileName);
        try {
            workbook = Workbook.createWorkbook(file);
        }
        catch (IOException e) {
            log.error("Create workbook eror", e);
            return;
        }

        int index = 0;
        for (Iterator iter = sheetName2RecordListMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Entry) iter.next();
            String sheetName = (String) entry.getKey(); // key 工作表名称
            List recordList = (List) entry.getValue(); // value 工作表上的记录

            WritableSheet sheet = workbook.createSheet(sheetName, index++);

            // 创建首行信息栏
            for (int c = 0; c < fieldTitles.length; c++) {
                String title = fieldTitles[c];
                try {
                    addCell(sheet, 0, c, title);
                }
                catch (Exception e) {
                    log.error("Add cell value[" + title + "] to sheet[" + sheet.getName() + "] error", e);
                    return;
                }
            }

            // 写入每条记录
            for (int i = 0, row = 1, m = recordList.size(); i < m; i++, row++) {
                Map field2ValueMap = (Map) recordList.get(i);

                for (int c = 0; c < fieldTitles.length; c++) {
                    Object value = field2ValueMap.get(fieldTitles[c]);
                    try {
                        addCell(sheet, row, c, value);
                    }
                    catch (Exception e) {
                        log.error("Add cell value[" + value + "] to sheet[" + sheet.getName() + "] error", e);
                        return;
                    }
                }
            }
        }
        try {
            workbook.write();
            workbook.close();
        }
        catch (WriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void addCell(WritableSheet sheet, int row, int col, Object value) throws Exception {
        WritableCell cell = null;
        if (value == null) {
            cell = new Blank(col, row);
        }
        else if (value instanceof String) {
            cell = new Label(col, row, (String) value);
        }
        else if (value instanceof Boolean) {
            cell = new jxl.write.Boolean(col, row, ((Boolean) value).booleanValue());
        }
        else if (value instanceof Integer) {
            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            cell = new Number(col, row, ((Integer) value).intValue(), integerFormat);
        }
        else if (value instanceof Double) {
            cell = new Number(col, row, ((Double) value).doubleValue());
        }
        else if (value instanceof Date) {
            DateFormat customDateFormat = new DateFormat("yyyy-m-d");
            WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
            cell = new DateTime(col, row, (Date) value, dateFormat);
        }
        else {
            cell = new Label(col, row, value.toString());
        }
        sheet.addCell(cell);

        int width = StringUtils.getRealLength(cell.getContents()) + 1;
        int maxWidth = sheet.getColumnView(col).getSize() / 256;
        if (width > maxWidth) {
            sheet.setColumnView(col, width);
        }
    }

    /**
     * 
     * @param sheet
     * @param row
     * @param col
     * @param rowspan
     *            跨越几行 默认为1
     * @param colspan
     *            跨越几列 默认为1
     * @param value
     * @throws Exception
     */
    private void addCell(WritableSheet sheet, int row, int col, int rowspan, int colspan, Object value)
            throws Exception {
        WritableCell cell = null;
        if (value == null) {
            cell = new Blank(col, row);
        }
        else if (value instanceof String) {
            cell = new Label(col, row, (String) value);
        }
        else if (value instanceof Boolean) {
            cell = new jxl.write.Boolean(col, row, ((Boolean) value).booleanValue());
        }
        else if (value instanceof Integer) {
            WritableCellFormat integerFormat = new WritableCellFormat(NumberFormats.INTEGER);
            cell = new Number(col, row, ((Integer) value).intValue(), integerFormat);
        }
        else if (value instanceof Double) {
            cell = new Number(col, row, ((Double) value).doubleValue());
        }
        else if (value instanceof Date) {
            DateFormat customDateFormat = new DateFormat("yyyy-m-d");
            WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
            cell = new DateTime(col, row, (Date) value, dateFormat);
        }
        else {
            cell = new Label(col, row, value.toString());
        }
        sheet.addCell(cell);

        int width = StringUtils.getRealLength(cell.getContents()) + 1;
        int maxWidth = sheet.getColumnView(col).getSize() / 256;
        if (width > maxWidth) {
            sheet.setColumnView(col, width);
        }

        if (rowspan != 1 || colspan != 1) {
            sheet.mergeCells(col, row, col + colspan - 1, row + rowspan - 1);
            WritableCell wCell = sheet.getWritableCell(col, row);
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            wCell.setCellFormat(cellFormat);
        }
    }
}
