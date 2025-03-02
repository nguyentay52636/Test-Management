package org.example.GUI.FormDialog.DialogExam;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SubmitConfirmDialog extends JDialog {

    private boolean confirmed = false; // Biến lưu trạng thái xác nhận

    public SubmitConfirmDialog(JFrame parent) {
        super(parent, "Xác nhận nộp bài", true);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setSize(400, 180);
        setLocationRelativeTo(parent);

        // Tiêu đề
        JLabel label = new JLabel("Bạn có chắc muốn nộp bài?", JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(0, 20, 400, 30);
        add(label);

        // Nút "Chưa"
        JButton cancelButton = new JButton("Chưa");
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        cancelButton.setBounds(50, 90, 120, 40);
        cancelButton.setBackground(Color.LIGHT_GRAY);
        add(cancelButton);

        // Nút "Dạ chắc"
        JButton confirmButton = new JButton("Chắc");
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmButton.setBounds(230, 90, 120, 40);
        confirmButton.setBackground(Color.RED);
        confirmButton.setForeground(Color.WHITE);
        add(confirmButton);

        // Sự kiện nút "Chưa"
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose(); // Đóng hộp thoại
            }
        });

        // Sự kiện nút "chắc"
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose(); // Đóng hộp thoại
            }
        });
    }

    public boolean isConfirmed() {
        return confirmed; // Trả về kết quả xác nhận
    }
}
