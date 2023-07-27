/*
 * Copyright (c) 2023. Carlos.GarciaSilva@solera.com
 * All rights reserved to QapterClaims FR team
 */

package qa.tools.utils;

import static org.apache.poi.common.usermodel.Hyperlink.LINK_URL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qa.tools.constants.ExcelConstants;
import qa.tools.constants.ExcelConstants.ExcelFields;
import qa.tools.models.TestRailCase;

public class ExcelUtils {

    private static final Logger logger = Logger.getLogger(ExcelUtils.class.getName());

    private ExcelUtils() {
        throw new IllegalStateException("ExcelUtils class");
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public static List<TestRailCase> mergePostponedCases(Map<String, TestRailCase> previous,
            List<TestRailCase> actual) {
        List<TestRailCase> temp = new ArrayList<>();
        for (TestRailCase test : actual) {
            String id = test.getCaseHyperlinkLabel();
            if (previous.containsKey(id)) {
                TestRailCase tmp = previous.get(id);
                test.setAssigned(tmp.getAssigned());
                test.setDescription(tmp.getDescription());
                test.setSolution(tmp.getSolution());
                test.setSolHyperlinkAddress(tmp.getSolHyperlinkAddress());
                test.setSolHyperlinkLabel(tmp.getSolHyperlinkLabel());
                test.setStatus(tmp.getStatus());
            }
            temp.add(test);
        }
        return temp;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public static Map<String, TestRailCase> convertExcel2Hashmap(File file) {
        Map<String, TestRailCase> map = new HashMap<>();

        try (FileInputStream inputStream = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(
                inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            int regressionRow = findPreviousRegressionRow(sheet);
            Iterator<Row> rowIterator = sheet.getRow(regressionRow).getSheet().rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                final String assignedTo = cellIterator.next().getStringCellValue();

                final String failRatio = cellIterator.next().getStringCellValue();

                Cell aux = cellIterator.next();
                String titleHyperlinkAddress;
                String titleHyperlinkLabel;
                if (aux.getHyperlink() == null) {
                    titleHyperlinkAddress = "";
                } else {
                    titleHyperlinkAddress = aux.getHyperlink().getAddress();
                }
                titleHyperlinkLabel = aux.getStringCellValue();

                aux = cellIterator.next();
                String caseHyperlinkAddress;
                String caseHyperlinkLabel;
                if (aux.getHyperlink() == null) {
                    caseHyperlinkAddress = "";
                } else {
                    caseHyperlinkAddress = aux.getHyperlink().getAddress();
                }
                caseHyperlinkLabel = aux.getStringCellValue();

                final String tesStatus = cellIterator.next().getStringCellValue();

                final String section = cellIterator.next().getStringCellValue();

                final String description = cellIterator.next().getStringCellValue();

                final String solution = cellIterator.next().getStringCellValue();

                aux = cellIterator.next();
                String solHyperlinkAddress;
                String solHyperlinkLabel;
                if (aux.getHyperlink() == null) {
                    solHyperlinkAddress = "";
                } else if (aux.getHyperlink().getAddress() == null) {
                    solHyperlinkAddress = "";
                } else {
                    solHyperlinkAddress = aux.getHyperlink().getAddress();
                }
                solHyperlinkLabel = aux.getStringCellValue();

                final String status = cellIterator.next().getStringCellValue();

                TestRailCase temp = new TestRailCase(
                        assignedTo,
                        failRatio,
                        new String[]{titleHyperlinkAddress, titleHyperlinkLabel},
                        new String[]{caseHyperlinkAddress, caseHyperlinkLabel},
                        tesStatus,
                        section,
                        description,
                        solution,
                        new String[]{solHyperlinkAddress, solHyperlinkLabel},
                        status
                );
                map.put(caseHyperlinkLabel, temp);
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException: ", e);
        }
        return map;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    public static void convertTestCase2Excel(File file, List<TestRailCase> cases) {
        try (FileInputStream fileInputStream = new FileInputStream(file); XSSFWorkbook workBook = new XSSFWorkbook(
                fileInputStream)) {

            XSSFSheet sheet = workBook.getSheetAt(0);

            // Initialize rows and columns
            int rownNum = sheet.getLastRowNum() + 1;
            int cellnum = 0;

            // Writing empty row
            Row row = sheet.createRow(rownNum++);
            for (int i = 0; i < ExcelFields.values().length; i++) {
                row.createCell(cellnum++).setCellValue(" ");
            }

            // Writing Date row
            row = sheet.createRow(rownNum++);
            cellnum = 0;
            Cell cell = row.createCell(cellnum++);
            cell.setCellStyle(getHeaderStyle(sheet.getWorkbook().createCellStyle()));
            cell.setCellValue(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDateTime.now()));
            for (int i = 1; i < ExcelFields.values().length; i++) {
                row.createCell(cellnum++).setCellValue(" ");
            }

            // Writing Title row
            row = sheet.createRow(rownNum++);
            cellnum = 0;
            for (ExcelFields field : ExcelFields.values()) {
                cell = row.createCell(cellnum++);
                cell.setCellStyle(getHeaderStyle(sheet.getWorkbook().createCellStyle()));
                cell.setCellValue(field.toString());
            }

            // Writing testcases
            int rowValidation = rownNum;
            int color = 0;
            for (int i = 0; i < cases.size(); i++) {
                color = setSectionColor(i, color, cases);
                CellStyle style = getStyle(color, sheet.getWorkbook().createCellStyle());

                Row rowTemp = sheet.createRow(rownNum++);
                int cellnumTemp = 0;
                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                TestRailCase testRailCase = cases.get(i);
                cell.setCellValue(testRailCase.getAssigned());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                cell.setCellValue(testRailCase.getFailRatio());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                XSSFHyperlink link = workBook.getCreationHelper().createHyperlink(LINK_URL);
                link.setAddress(testRailCase.getTitleHyperlinkAddress());
                cell.setHyperlink(link);
                cell.setCellValue(testRailCase.getTitleHyperlinkLabel());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                link = workBook.getCreationHelper().createHyperlink(LINK_URL);
                link.setAddress(testRailCase.getCaseHyperlinkAddress());
                cell.setHyperlink(link);
                cell.setCellValue(testRailCase.getCaseHyperlinkLabel());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                cell.setCellValue(testRailCase.getTestRailStatus());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                cell.setCellValue(testRailCase.getSection());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                cell.setCellValue(testRailCase.getDescription());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                cell.setCellValue(testRailCase.getSolution());

                cell = rowTemp.createCell(cellnumTemp++);
                cell.setCellStyle(style);
                link = workBook.getCreationHelper().createHyperlink(LINK_URL);
                link.setAddress(testRailCase.getSolHyperlinkAddress());
                cell.setHyperlink(link);
                cell.setCellValue(testRailCase.getSolHyperlinkLabel());

                cell = rowTemp.createCell(cellnumTemp);
                cell.setCellStyle(style);
                cell.setCellValue(testRailCase.getStatus());
            }

            DataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);
            CellRangeAddressList addressList = new CellRangeAddressList(rowValidation, rowValidation + cases.size() - 1,
                    9,
                    9);
            DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(
                    ExcelConstants.getStatus());
            DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
            dataValidation.setSuppressDropDownArrow(true);
            sheet.addValidationData(dataValidation);

            FileOutputStream outputStream = new FileOutputStream(file);
            workBook.write(outputStream);

            outputStream.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "IOException: ", e);
        }

    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private static CellStyle getHeaderStyle(CellStyle style) {
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());

        return style;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private static CellStyle getStyle(int color, CellStyle style) {
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setWrapText(true);
        style.setFillForegroundColor(ExcelConstants.getColors()[color].getIndex());

        return style;
    }

    //   --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> --> -->
    private static int setSectionColor(int i, int color, List<TestRailCase> cases) {
        if (i == 0) {
            return 0;
        } else {
            if (cases.get(i - 1).getSection().equals(cases.get(i).getSection())) {
                return color;
            } else {
                if (color == ExcelConstants.getColors().length - 1) {
                    return 0;
                } else {
                    return ++color;
                }
            }
        }
    }

    private static int findPreviousRegressionRow(XSSFSheet sheet) {
        int rowNum = sheet.getLastRowNum();
        if (rowNum == 0) {
            return 0;
        } else {
            for (int i = rowNum; i >= 0; i--) {
                if (sheet.getRow(i) != null && sheet.getRow(i).getCell(0) != null && sheet.getRow(i)
                        .getCell(0)
                        .getStringCellValue()
                        .equals(ExcelFields.ASSIGNED_TO.toString())) {
                    return i;
                }
            }
        }
        return rowNum;
    }

}
