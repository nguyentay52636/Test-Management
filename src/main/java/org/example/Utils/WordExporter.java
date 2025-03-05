package org.example.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.DTO.QuestionDTO;

public class WordExporter {
    public static void exportExam(String fileName, String title, List<QuestionDTO> questions) {
        try (XWPFDocument document = new XWPFDocument();
                FileOutputStream out = new FileOutputStream(fileName)) {

            // Tiêu đề đề thi
            XWPFParagraph titleParagraph = document.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = titleParagraph.createRun();
            titleRun.setText(title);
            titleRun.setBold(true);
            titleRun.setFontSize(16);

            // Xuống dòng
            document.createParagraph();

            // Danh sách câu hỏi
            int questionNumber = 1;
            for (QuestionDTO q : questions) {
                // Câu hỏi
                XWPFParagraph questionParagraph = document.createParagraph();
                XWPFRun questionRun = questionParagraph.createRun();
                questionRun.setText(questionNumber + ". " + q.getQContent());
                questionRun.setBold(true);

                // Chèn hình ảnh (nếu có)
                if (q.getQPicture() != null && !q.getQPicture().isEmpty()) {
                    addImageToDocument(document, q.getQPicture());
                }

                // Xuống dòng sau mỗi câu hỏi
                document.createParagraph();
                questionNumber++;
            }

            // Ghi file
            document.write(out);
            System.out.println("Xuất file Word thành công: " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addImageToDocument(XWPFDocument document, String imagePath) {
        try (FileInputStream is = new FileInputStream(new File(imagePath))) {
            XWPFParagraph imgParagraph = document.createParagraph();
            XWPFRun imgRun = imgParagraph.createRun();
            imgRun.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imagePath, 300, 200); // Ảnh 300x200
        } catch (Exception e) {
            System.out.println("Không thể thêm hình ảnh: " + imagePath);
            e.printStackTrace();
        }
    }
}