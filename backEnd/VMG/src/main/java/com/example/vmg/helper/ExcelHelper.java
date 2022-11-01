package com.example.vmg.helper;

import com.example.vmg.model.Department;
import com.example.vmg.model.Staff;
import com.example.vmg.respository.DepartmentRepository;
import com.example.vmg.service.DepartmentService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Code", "Name", "Date", "Email", "WelfareMoney", "Status", "Department" };
    static String SHEET = "Staffs";
    @Autowired DepartmentService departmentService;
    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public List<Staff> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Staff> staffs = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Staff staff = new Staff();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {

                        case 0:
                            staff.setCode(currentCell.getStringCellValue());
                            break;

                        case 1:
                            staff.setName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            staff.setDate(currentCell.getDateCellValue());
                            break;

                        case 3:
                            staff.setEmail(currentCell.getStringCellValue());
                            break;

                        case 4:
                            staff.setWelfareMoney(new BigDecimal(currentCell.getNumericCellValue()));
                            break;

                        case 5:
                            staff.setStatus(currentCell.getColumnIndex());
                            break;
                        case 6:
                            String name = currentCell.getStringCellValue();
                            Department department = new Department();
                            department = departmentService.getByName(currentCell.getStringCellValue());
                            System.out.println(department);
                            staff.setDepartment(department);
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                staffs.add(staff);
            }

            workbook.close();

            return staffs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
