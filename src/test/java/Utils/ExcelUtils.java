package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static XSSFRow Row;
    public static XSSFCell Cell;

    public static String file_path = System.getProperty("user.dir") + "\\" +
            PropertiesFile.getProperty("excel.path") + "\\" +
            PropertiesFile.getProperty("excel.name");

    // ✅ Load workbook and sheet (must call this before using setCellData etc.)
    public static void loadSheet(String sheetName) {
        try {
            fis = new FileInputStream(file_path);
            wb = new XSSFWorkbook(fis);
            sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in file: " + file_path);
            }
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Error loading Excel file: " + file_path, e);
        }
    }

    // ✅ Close workbook manually when done
    public static void closeWorkbook() {
        try {
            if (wb != null) {
                wb.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Read Excel as List<Map<Header, Value>>
    public static List<Map<String, String>> getSheetDataAsListOfMaps(String sheetName) {
        List<Map<String, String>> sheetData = new ArrayList<>();
        try {
            loadSheet(sheetName);

            XSSFRow headerRow = sheet.getRow(0);
            if (headerRow == null) return sheetData;

            int rowCount = sheet.getLastRowNum();
            int cellCount = headerRow.getLastCellNum();

            for (int i = 1; i <= rowCount; i++) {
                XSSFRow currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < cellCount; j++) {
                    XSSFCell headerCell = headerRow.getCell(j);
                    XSSFCell dataCell = currentRow.getCell(j);

                    String header = headerCell != null ? headerCell.getStringCellValue() : "Column" + j;
                    String value = "";

                    if (dataCell != null) {
                        switch (dataCell.getCellType()) {
                            case STRING:
                                value = dataCell.getStringCellValue();
                                break;
                            case NUMERIC:
                                value = NumberToTextConverter.toText(dataCell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                value = String.valueOf(dataCell.getBooleanCellValue());
                                break;
                            default:
                                value = "";
                        }
                    }
                    rowData.put(header, value);
                }

                sheetData.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sheetData;
    }

    // ✅ Write data to a cell safely
    public static void setCellData(String sheetName, int row, int col, String data) {
        try {
            loadSheet(sheetName);

            Row = sheet.getRow(row);
            if (Row == null) Row = sheet.createRow(row);

            Cell = Row.getCell(col);
            if (Cell == null) Cell = Row.createCell(col);

            Cell.setCellValue(data);

            fos = new FileOutputStream(file_path);
            wb.write(fos);

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get cell index by header
    public static int getCellIndexByHeader(String sheetName, String headerName) {
        try {
            loadSheet(sheetName);

            XSSFRow headerRow = sheet.getRow(0);
            if (headerRow != null) {
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    XSSFCell cell = headerRow.getCell(i);
                    if (cell != null && cell.getStringCellValue().equalsIgnoreCase(headerName)) {
                        return i;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // ✅ Fill cell with green color
    public static void fillCellGreen(String sheetName, int row, int col) {
        try {
            loadSheet(sheetName);

            XSSFCell cell = sheet.getRow(row).getCell(col);
            XSSFCellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            fos = new FileOutputStream(file_path);
            wb.write(fos);
            fos.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    // ✅ Fill cell with red color
    public static void fillCellRed(String sheetName, int row, int col) {
        try {
            loadSheet(sheetName);

            XSSFCell cell = sheet.getRow(row).getCell(col);
            XSSFCellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            fos = new FileOutputStream(file_path);
            wb.write(fos);
            fos.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    // ✅ Optional: get row count
    public static int getRowCount(String sheetName) {
        try {
            loadSheet(sheetName);
            return sheet.getLastRowNum();
        } catch (Exception e) {
            return 0;
        }
    }

    // ✅ Optional: get cell count
    public static int getCellCount(String sheetName) {
        try {
            loadSheet(sheetName);
            return sheet.getRow(0).getLastCellNum();
        } catch (Exception e) {
            return 0;
        }
    }

    // ✅ Optional: get data from cell
    public static String getCellData(String sheetName, int row, int col) {
        try {
            loadSheet(sheetName);
            XSSFCell cell = sheet.getRow(row).getCell(col);
            if (cell.getCellType() == CellType.NUMERIC)
                return NumberToTextConverter.toText(cell.getNumericCellValue());
            else
                return cell.getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }
}
