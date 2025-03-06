package org.example.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.DTO.QuestionDTO;
import org.example.DTO.UsersDTO;

public class importExcel {
    public static List<QuestionDTO> readExcel(String filePath) {
        List<QuestionDTO> questions = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); 
            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue;

                int questionID = (int) row.getCell(0).getNumericCellValue();
                String qContent = row.getCell(1).getStringCellValue();
                String qPictures = row.getCell(2).getStringCellValue();
                int qTopicID = (int) row.getCell(3).getNumericCellValue();
                String qLevel = row.getCell(4).getStringCellValue();
                Boolean qStatus = row.getCell(5).getNumericCellValue() == 1;

                questions.add(new QuestionDTO(questionID, qContent, qPictures, qTopicID, qLevel, qStatus));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
    public static ArrayList<UsersDTO> readUsersExcel(String filePath) {
        ArrayList<UsersDTO> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
    
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Bỏ qua tiêu đề
    
                int userID = (int) row.getCell(0).getNumericCellValue();
                String userName = row.getCell(1).getStringCellValue();
                String userEmail = row.getCell(2).getStringCellValue();
                String userPassword = row.getCell(3).getStringCellValue();
                String userFullName = (row.getCell(4) != null) ? row.getCell(4).getStringCellValue() : "";
                Boolean isAdmin = row.getCell(5) != null && row.getCell(5).getNumericCellValue() == 1;
    
                users.add(new UsersDTO(userID, userName, userEmail, userPassword, userFullName, isAdmin));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
}