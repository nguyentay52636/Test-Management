package org.example.Utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.DTO.QuestionDTO;

public class WordExporter {
    public static void exportExamToDocx(List<QuestionDTO> questions, String fileName) {
        try (XWPFDocument document = new XWPFDocument()) {
            // Tiêu đề chính
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("ĐỀ THI TRẮC NGHIỆM");
            titleRun.setBold(true);
            titleRun.setFontSize(16);
            titleRun.addBreak(); // Xuống dòng

            int questionNumber = 1;
            for (QuestionDTO q : questions) {
                // Câu hỏi
                XWPFParagraph questionPara = document.createParagraph();
                XWPFRun questionRun = questionPara.createRun();
                questionRun.setText(questionNumber + ". " + q.getQContent());
                questionRun.setBold(true);
                questionRun.addBreak();

                // Thêm ảnh nếu có
                if (q.getQPicture() != null && !q.getQPicture().isEmpty()) {
                    try (InputStream imageStream = WordExporter.class.getResourceAsStream(q.getQPicture())) {
                        XWPFParagraph imgPara = document.createParagraph();
                        XWPFRun imgRun = imgPara.createRun();
                        imgRun.addPicture(
                                imageStream,
                                XWPFDocument.PICTURE_TYPE_PNG,
                                q.getQPicture(),
                                300, 150);
                    } catch (Exception e) {
                        System.out.println("Không thể chèn ảnh: " + e.getMessage());
                    }
                }

                // Đáp án mẫu (hard-coded as per your example)
                String[] options = {
                        "A. tay la la toi.",
                        "B. khong phai toi",
                        "C. là traidep nhat.",
                        "D. it gioi"
                };

                for (String option : options) {
                    XWPFParagraph optionPara = document.createParagraph();
                    optionPara.setIndentationLeft(400); // Thụt lề sang phải
                    XWPFRun optionRun = optionPara.createRun();
                    optionRun.setText(option);
                }

                questionNumber++;
            }

            // Ghi file
            try (FileOutputStream out = new FileOutputStream(fileName)) {
                document.write(out);
            }

            JOptionPane.showMessageDialog(null, "Đã xuất file " + fileName + " thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file: " + ex.getMessage());
        }
    }
}