package org.example.GUI.FormDialog.DialogMonHoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.example.BUS.AnswersBUS;
import org.example.BUS.QuestionBUS;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;

public class JPanelViewDetails extends JPanel {
    private JPanel contentPanel;
    private JPanel previousPanel;
    private int questionID;
    private JLabel txtID;
    private JLabel lblLoai;
    private JTextField txtCauHoi;
    private JLabel txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    private JLabel lblCorrectA, lblCorrectB, lblCorrectC, lblCorrectD;

    public JPanelViewDetails(JPanel contentPanel, JPanel previousPanel, int questionID) {
        this.contentPanel = contentPanel;
        this.previousPanel = previousPanel;
        this.questionID = questionID;
        initComponents();
        loadQuestionDetails();
    }

    private void initComponents() {
        setBackground(new Color(240, 242, 245));
        setLayout(null);
        setPreferredSize(new Dimension(900, 550));
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        // Header Section
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 123, 255));
        headerPanel.setBounds(0, 0, 900, 60);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("Chi Tiết Câu Hỏi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 15, 300, 30);
        headerPanel.add(lblTitle);

        // Content Section
        JLabel lblID = new JLabel("ID Câu Hỏi:");
        lblID.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblID.setForeground(new Color(51, 51, 51));
        lblID.setBounds(50, 80, 100, 25);
        add(lblID);

        txtID = new JLabel();
        txtID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtID.setBackground(Color.WHITE);
        txtID.setForeground(new Color(51, 51, 51));
        txtID.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtID.setBounds(150, 80, 200, 35);
        txtID.setOpaque(true);
        add(txtID);

        JLabel lblLoaiLabel = new JLabel("Mức Độ:");
        lblLoaiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLoaiLabel.setForeground(new Color(51, 51, 51));
        lblLoaiLabel.setBounds(400, 80, 100, 25);
        add(lblLoaiLabel);

        lblLoai = new JLabel();
        lblLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblLoai.setBackground(Color.WHITE);
        lblLoai.setForeground(new Color(51, 51, 51));
        lblLoai.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        lblLoai.setBounds(500, 80, 150, 35);
        lblLoai.setOpaque(true);
        add(lblLoai);

        JLabel lblCauHoi = new JLabel("Nội Dung Câu Hỏi:");
        lblCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCauHoi.setForeground(new Color(51, 51, 51));
        lblCauHoi.setBounds(50, 130, 150, 25);
        add(lblCauHoi);

        txtCauHoi = new JTextField();
        txtCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCauHoi.setBackground(Color.WHITE);
        txtCauHoi.setForeground(new Color(51, 51, 51));
        txtCauHoi.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtCauHoi.setBounds(200, 130, 650, 35);
        txtCauHoi.setEditable(false);
        add(txtCauHoi);

        // Answer Section
        JLabel lblDapAn = new JLabel("Đáp Án:");
        lblDapAn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDapAn.setForeground(new Color(51, 51, 51));
        lblDapAn.setBounds(50, 180, 100, 25);
        add(lblDapAn);

        // Answer A
        JLabel lblAnswerA = new JLabel("A:");
        lblAnswerA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAnswerA.setForeground(new Color(51, 51, 51));
        lblAnswerA.setBounds(80, 210, 50, 25);
        add(lblAnswerA);

        txtAnswerA = new JLabel();
        txtAnswerA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAnswerA.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtAnswerA.setBounds(130, 210, 700, 35);
        txtAnswerA.setOpaque(true);
        txtAnswerA.setBackground(Color.WHITE);
        add(txtAnswerA);

        lblCorrectA = new JLabel();
        lblCorrectA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCorrectA.setBounds(840, 210, 50, 35);
        add(lblCorrectA);

        // Answer B
        JLabel lblAnswerB = new JLabel("B:");
        lblAnswerB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAnswerB.setForeground(new Color(51, 51, 51));
        lblAnswerB.setBounds(80, 260, 50, 25);
        add(lblAnswerB);

        txtAnswerB = new JLabel();
        txtAnswerB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAnswerB.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtAnswerB.setBounds(130, 260, 700, 35);
        txtAnswerB.setOpaque(true);
        txtAnswerB.setBackground(Color.WHITE);
        add(txtAnswerB);

        lblCorrectB = new JLabel();
        lblCorrectB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCorrectB.setBounds(840, 260, 50, 35);
        add(lblCorrectB);

        // Answer C
        JLabel lblAnswerC = new JLabel("C:");
        lblAnswerC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAnswerC.setForeground(new Color(51, 51, 51));
        lblAnswerC.setBounds(80, 310, 50, 25);
        add(lblAnswerC);

        txtAnswerC = new JLabel();
        txtAnswerC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAnswerC.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtAnswerC.setBounds(130, 310, 700, 35);
        txtAnswerC.setOpaque(true);
        txtAnswerC.setBackground(Color.WHITE);
        add(txtAnswerC);

        lblCorrectC = new JLabel();
        lblCorrectC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCorrectC.setBounds(840, 310, 50, 35);
        add(lblCorrectC);

        // Answer D
        JLabel lblAnswerD = new JLabel("D:");
        lblAnswerD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAnswerD.setForeground(new Color(51, 51, 51));
        lblAnswerD.setBounds(80, 360, 50, 25);
        add(lblAnswerD);

        txtAnswerD = new JLabel();
        txtAnswerD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAnswerD.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        txtAnswerD.setBounds(130, 360, 700, 35);
        txtAnswerD.setOpaque(true);
        txtAnswerD.setBackground(Color.WHITE);
        add(txtAnswerD);

        lblCorrectD = new JLabel();
        lblCorrectD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCorrectD.setBounds(840, 360, 50, 35);
        add(lblCorrectD);

        // Exit Button
        JButton btnThoat = createStyledButton("Thoát", new Color(108, 117, 125), "Quay lại");
        btnThoat.setName("btnThoat");
        btnThoat.setBounds(400, 450, 150, 45);
        btnThoat.addActionListener(e -> returnToMain());
        add(btnThoat);
    }

    private JButton createStyledButton(String text, Color bgColor, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        button.setToolTipText(tooltip);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }
        });

        return button;
    }

    private void returnToMain() {
        if (contentPanel != null && previousPanel != null) {
            contentPanel.removeAll();
            contentPanel.add(previousPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    private void loadQuestionDetails() {
        QuestionBUS questionBUS = new QuestionBUS();
        QuestionDTO question = questionBUS.getQuestionByID(questionID);
        if (question == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy câu hỏi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtID.setText(String.valueOf(question.getQuestionID()));
        lblLoai.setText(question.getQLevel());
        txtCauHoi.setText(question.getQContent());

        AnswersBUS answersBUS = new AnswersBUS();
        List<AnswersDTO> answers = answersBUS.getAnswersByQuestionID(questionID);
        if (answers.size() != 4) {
            JOptionPane.showMessageDialog(this, "Không tải được đầy đủ đáp án!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Map answers to A, B, C, D (assuming order is A, B, C, D)
        for (int i = 0; i < 4; i++) {
            AnswersDTO ans = answers.get(i);
            switch (Character.toString('A' + i)) {
                case "A":
                    txtAnswerA.setText(ans.getAwContent());
                    lblCorrectA.setText(ans.getIsStatus() ? "Đúng" : "Sai");
                    lblCorrectA.setForeground(ans.getIsStatus() ? new Color(0, 153, 0) : new Color(153, 0, 0));
                    break;
                case "B":
                    txtAnswerB.setText(ans.getAwContent());
                    lblCorrectB.setText(ans.getIsStatus() ? "Đúng" : "Sai");
                    lblCorrectB.setForeground(ans.getIsStatus() ? new Color(0, 153, 0) : new Color(153, 0, 0));
                    break;
                case "C":
                    txtAnswerC.setText(ans.getAwContent());
                    lblCorrectC.setText(ans.getIsStatus() ? "Đúng" : "Sai");
                    lblCorrectC.setForeground(ans.getIsStatus() ? new Color(0, 153, 0) : new Color(153, 0, 0));
                    break;
                case "D":
                    txtAnswerD.setText(ans.getAwContent());
                    lblCorrectD.setText(ans.getIsStatus() ? "Đúng" : "Sai");
                    lblCorrectD.setForeground(ans.getIsStatus() ? new Color(0, 153, 0) : new Color(153, 0, 0));
                    break;
            }
        }
    }
}