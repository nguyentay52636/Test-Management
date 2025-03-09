package org.example.GUI.FormDialog.DialogMonHoc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.example.BUS.AnswersBUS;
import org.example.BUS.QuestionBUS;
import org.example.DTO.AnswersDTO;
import org.example.DTO.QuestionDTO;

public class JPanelSuaCauHoi extends JPanel {
    private JPanel contentPanel;
    private JPanel previousPanel;
    private QuestionDTO question;
    private QuestionBUS questionBUS;
    private AnswersBUS answersBUS;
    private List<AnswersDTO> originalAnswers;

    // UI Components as instance variables for access
    private JTextField txtID;
    private JComboBox<String> cboLoai;
    private JTextField txtCauHoi;
    private JCheckBox chkA, chkB, chkC, chkD;
    private JTextField txtA, txtB, txtC, txtD;

    public JPanelSuaCauHoi(JPanel contentPanel, JPanel previousPanel, QuestionDTO question) {
        this.contentPanel = contentPanel;
        this.previousPanel = previousPanel;
        this.question = question;
        this.questionBUS = new QuestionBUS();
        this.answersBUS = new AnswersBUS();
        // Fixed: Use getAnswersByQuestionId to fetch List<AnswersDTO>
        this.originalAnswers = questionBUS.getAnswersByQuestionId(question.getQuestionID()); // Fetch answers
        initComponents();
        populateFields();
    }

    private void initComponents() {
        setBackground(new Color(245, 247, 250));
        setLayout(null);
        setPreferredSize(new Dimension(900, 550));
        setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 2, true));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 123, 255));
        headerPanel.setBounds(0, 0, 900, 60);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel lblTitle = new JLabel("Sửa Câu Hỏi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(20, 15, 300, 30);
        headerPanel.add(lblTitle);

        JLabel lblID = new JLabel("ID Câu Hỏi:");
        lblID.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblID.setForeground(new Color(51, 51, 51));
        lblID.setBounds(50, 80, 100, 25);
        add(lblID);

        txtID = new JTextField();
        txtID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtID.setBackground(new Color(230, 235, 240));
        txtID.setForeground(new Color(51, 51, 51));
        txtID.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        txtID.setBounds(150, 80, 200, 35);
        txtID.setEditable(false);
        add(txtID);

        JLabel lblLoai = new JLabel("Mức Độ:");
        lblLoai.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblLoai.setForeground(new Color(51, 51, 51));
        lblLoai.setBounds(400, 80, 100, 25);
        add(lblLoai);

        cboLoai = new JComboBox<>(new String[] { "Dễ", "Trung bình", "Khó" });
        cboLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboLoai.setBackground(new Color(230, 235, 240));
        cboLoai.setForeground(new Color(51, 51, 51));
        cboLoai.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        cboLoai.setBounds(500, 80, 150, 35);
        add(cboLoai);

        JLabel lblCauHoi = new JLabel("Nội Dung Câu Hỏi:");
        lblCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCauHoi.setForeground(new Color(51, 51, 51));
        lblCauHoi.setBounds(50, 130, 150, 25);
        add(lblCauHoi);

        txtCauHoi = new JTextField();
        txtCauHoi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCauHoi.setBackground(Color.WHITE);
        txtCauHoi.setForeground(new Color(51, 51, 51));
        txtCauHoi.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        txtCauHoi.setBounds(200, 130, 650, 35);
        add(txtCauHoi);

        JLabel lblDapAn = new JLabel("Đáp Án:");
        lblDapAn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDapAn.setForeground(new Color(51, 51, 51));
        lblDapAn.setBounds(50, 180, 100, 25);
        add(lblDapAn);

        chkA = new JCheckBox("A");
        chkA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkA.setBackground(new Color(245, 247, 250));
        chkA.setBounds(80, 210, 50, 25);
        add(chkA);
        txtA = new JTextField();
        txtA.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtA.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        txtA.setBounds(150, 210, 700, 35);
        add(txtA);

        chkB = new JCheckBox("B");
        chkB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkB.setBackground(new Color(245, 247, 250));
        chkB.setBounds(80, 260, 50, 25);
        add(chkB);
        txtB = new JTextField();
        txtB.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtB.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        txtB.setBounds(150, 260, 700, 35);
        add(txtB);

        chkC = new JCheckBox("C");
        chkC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkC.setBackground(new Color(245, 247, 250));
        chkC.setBounds(80, 310, 50, 25);
        add(chkC);
        txtC = new JTextField();
        txtC.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtC.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        txtC.setBounds(150, 310, 700, 35);
        add(txtC);

        chkD = new JCheckBox("D");
        chkD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkD.setBackground(new Color(245, 247, 250));
        chkD.setBounds(80, 360, 50, 25);
        add(chkD);
        txtD = new JTextField();
        txtD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtD.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1));
        txtD.setBounds(150, 360, 700, 35);
        add(txtD);

        JButton btnThoat = createStyledButton("Hủy", new Color(108, 117, 125), "Hủy và quay lại");
        btnThoat.setBounds(250, 450, 150, 45);
        btnThoat.addActionListener(e -> closeDialog());
        add(btnThoat);

        JButton btnChapNhan = createStyledButton("Xác Nhận", new Color(40, 167, 69), "Cập nhật câu hỏi trong hệ thống");
        btnChapNhan.setBounds(500, 450, 150, 45);
        btnChapNhan.addActionListener(e -> saveAndClose());
        add(btnChapNhan);
    }

    private JButton createStyledButton(String text, Color bgColor, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 200), 1, true));
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
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void populateFields() {
        txtID.setText(String.valueOf(question.getQuestionID()));
        cboLoai.setSelectedItem(question.getQLevel());
        txtCauHoi.setText(question.getQContent());

        if (originalAnswers != null && !originalAnswers.isEmpty()) {
            if (originalAnswers.size() > 0) {
                chkA.setSelected(originalAnswers.get(0).getIsRight());
                txtA.setText(originalAnswers.get(0).getAwContent());
            }
            if (originalAnswers.size() > 1) {
                chkB.setSelected(originalAnswers.get(1).getIsRight());
                txtB.setText(originalAnswers.get(1).getAwContent());
            }
            if (originalAnswers.size() > 2) {
                chkC.setSelected(originalAnswers.get(2).getIsRight());
                txtC.setText(originalAnswers.get(2).getAwContent());
            }
            if (originalAnswers.size() > 3) {
                chkD.setSelected(originalAnswers.get(3).getIsRight());
                txtD.setText(originalAnswers.get(3).getAwContent());
            }
        }
    }

    private void saveAndClose() {
        if (!validateFields()) {
            return;
        }

        // Update QuestionDTO
        QuestionDTO updatedQuestion = new QuestionDTO();
        updatedQuestion.setQuestionID(question.getQuestionID());
        updatedQuestion.setQLevel((String) cboLoai.getSelectedItem());
        updatedQuestion.setQContent(txtCauHoi.getText());
        updatedQuestion.setQTopicID(question.getQTopicID());
        updatedQuestion.setQStatus(question.getQStatus());
        updatedQuestion.setQPicture(question.getQPicture());

        // Update AnswersDTO separately
        List<AnswersDTO> updatedAnswers = createUpdatedAnswers();

        // Save question and answers independently
        boolean questionUpdated = questionBUS.updateQuestion(updatedQuestion);
        boolean answersUpdated = answersBUS.updateAnswers(updatedAnswers);

        if (questionUpdated && answersUpdated) {
            JOptionPane.showMessageDialog(this, "Cập nhật câu hỏi và đáp án thành công!", "Thành công",
                    JOptionPane.INFORMATION_MESSAGE);
            closeDialog();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (txtCauHoi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nội dung câu hỏi không được để trống!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        boolean hasCorrectAnswer = chkA.isSelected() || chkB.isSelected() || chkC.isSelected() || chkD.isSelected();
        if (!hasCorrectAnswer) {
            JOptionPane.showMessageDialog(this, "Phải chọn ít nhất một đáp án đúng!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private List<AnswersDTO> createUpdatedAnswers() {
        List<AnswersDTO> answers = new ArrayList<>();

        answers.add(new AnswersDTO(
                originalAnswers != null && originalAnswers.size() > 0 ? originalAnswers.get(0).getAwID() : 0,
                question.getQuestionID(),
                txtA.getText(),
                originalAnswers != null && originalAnswers.size() > 0 ? originalAnswers.get(0).getAwPicture() : null,
                chkA.isSelected(),
                originalAnswers != null && originalAnswers.size() > 0 ? originalAnswers.get(0).getIsStatus() : true));
        answers.add(new AnswersDTO(
                originalAnswers != null && originalAnswers.size() > 1 ? originalAnswers.get(1).getAwID() : 0,
                question.getQuestionID(),
                txtB.getText(),
                originalAnswers != null && originalAnswers.size() > 1 ? originalAnswers.get(1).getAwPicture() : null,
                chkB.isSelected(),
                originalAnswers != null && originalAnswers.size() > 1 ? originalAnswers.get(1).getIsStatus() : true));
        answers.add(new AnswersDTO(
                originalAnswers != null && originalAnswers.size() > 2 ? originalAnswers.get(2).getAwID() : 0,
                question.getQuestionID(),
                txtC.getText(),
                originalAnswers != null && originalAnswers.size() > 2 ? originalAnswers.get(2).getAwPicture() : null,
                chkC.isSelected(),
                originalAnswers != null && originalAnswers.size() > 2 ? originalAnswers.get(2).getIsStatus() : true));
        answers.add(new AnswersDTO(
                originalAnswers != null && originalAnswers.size() > 3 ? originalAnswers.get(3).getAwID() : 0,
                question.getQuestionID(),
                txtD.getText(),
                originalAnswers != null && originalAnswers.size() > 3 ? originalAnswers.get(3).getAwPicture() : null,
                chkD.isSelected(),
                originalAnswers != null && originalAnswers.size() > 3 ? originalAnswers.get(3).getIsStatus() : true));

        return answers;
    }

    private void closeDialog() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}