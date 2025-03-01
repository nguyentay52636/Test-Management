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

public class importExcel {
    public static List<QuestionDTO> readExcel(String filePath) {
        List<QuestionDTO> questions = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue; // Bỏ qua dòng tiêu đề

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

    // public static void main(String[] args) {
    // String filePath = "D:/tracnghiem.xlsx"; // Thay đường dẫn file của bạn
    // List<QuestionDTO> questions = readExcel(filePath);
    //
    // QuestionDAO dao = new QuestionDAO();
    // for (QuestionDTO q : questions) {
    // if (dao.insertQuestion(q)) {
    // System.out.println("Thêm câu hỏi thành công: " + q.getQContent());
    // } else {
    // System.out.println("Lỗi khi thêm câu hỏi: " + q.getQContent());
    // }
    // }
    // }
}
