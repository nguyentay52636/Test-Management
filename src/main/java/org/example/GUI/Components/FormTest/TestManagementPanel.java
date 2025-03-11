package org.example.GUI.Components.FormTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class TestManagementPanel extends JPanel {
    private JTable testTable;
    private DefaultTableModel tableModel;
    private JPanel detailsPanel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private TestBUS testBUS;
    private JButton addButton, editButton, deleteButton, btnExportDocx;
    private JComboBox<String> cboTopic;
    private JTable questionTable;
    private DefaultTableModel questionTableModel;
    private List<Question> questionBank;

    // Question class (simplified for this example)
    class Question {
        String topic, text;

        public Question(String topic, String text) {
            this.topic = topic;
            this.text = text;
        }
    }

    public TestManagementPanel() {
        this.testBUS = new TestBUS();
        initializeQuestionBank();
        setLayout(new BorderLayout(0, 10));
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header Panel
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), 0, getHeight(),
                        new Color(41, 128, 185));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 60));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel titleLabel = new JLabel("Test Management");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(500);
        splitPane.setResizeWeight(0.5);
        splitPane.setBorder(BorderFactory.createEmptyBorder());

        // Left: Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout(0, 10));
        tablePanel.setOpaque(false);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(new Color(44, 62, 80));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        tablePanel.add(searchPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] { "Test ID", "Test Name", "Number of Questions", "Creation Date" }, 0);
        testTable = new JTable(tableModel);
        testTable.setRowHeight(30);
        testTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testTable.setShowGrid(true);
        testTable.setGridColor(new Color(220, 220, 220));
        testTable.setAutoCreateRowSorter(true);
        rowSorter = new TableRowSorter<>(tableModel);
        testTable.setRowSorter(rowSorter);

        JScrollPane tableScrollPane = new JScrollPane(testTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        splitPane.setLeftComponent(tablePanel);

        // Right: Details Panel
        detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));

        // Add Subject ComboBox and Question Table
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblSubject = new JLabel("Chọn Môn Học:");
        lblSubject.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubject.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(lblSubject, gbc);

        cboTopic = new JComboBox<>(new String[] { "Mathematics", "Physics", "Chemistry", "Biology" });
        cboTopic.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cboTopic.setBackground(new Color(60, 70, 90));
        cboTopic.setForeground(Color.WHITE);
        cboTopic.setBorder(BorderFactory.createLineBorder(new Color(100, 110, 130), 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        detailsPanel.add(cboTopic, gbc);

        questionTableModel = new DefaultTableModel(new String[] { "Question" }, 0);
        questionTable = new JTable(questionTableModel);
        questionTable.setRowHeight(25);
        JScrollPane questionScrollPane = new JScrollPane(questionTable);
        questionScrollPane.setPreferredSize(new Dimension(300, 200));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        detailsPanel.add(questionScrollPane, gbc);

        splitPane.setRightComponent(detailsPanel);
        add(splitPane, BorderLayout.CENTER);

        // Action Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setOpaque(false);
        addButton = createStyledButton("Add Test", new Color(52, 152, 219), Color.WHITE);
        editButton = createStyledButton("Edit Test", new Color(52, 152, 219), Color.WHITE);
        deleteButton = createStyledButton("Delete Test", new Color(217, 83, 79), Color.WHITE);
        btnExportDocx = createStyledButton("Export DOCX", new Color(46, 204, 113), Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(btnExportDocx);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        testTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean hasSelection = testTable.getSelectedRow() != -1;
                editButton.setEnabled(hasSelection);
                deleteButton.setEnabled(hasSelection);
                btnExportDocx.setEnabled(hasSelection);
                if (hasSelection) {
                    updateDetailsPanel(testTable.getSelectedRow());
                } else {
                    clearDetailsPanel();
                }
            }
        });

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String text = searchField.getText().trim().toLowerCase();
                rowSorter.setRowFilter(text.isEmpty() ? null : RowFilter.regexFilter("(?i)" + text));
            }
        });

        cboTopic.addActionListener(e -> updateQuestionTable());

        btnExportDocx.addActionListener(e -> exportToDocx());
    }

    private void initializeQuestionBank() {
        questionBank = new ArrayList<>();
        questionBank.add(new Question("Mathematics", "What is 2 + 2?"));
        questionBank.add(new Question("Mathematics", "Solve: x² - 4 = 0"));
        questionBank.add(new Question("Physics", "What is the unit of force?"));
        questionBank.add(new Question("Physics", "F = ?"));
        questionBank.add(new Question("Chemistry", "What is H₂O?"));
        questionBank.add(new Question("Chemistry", "What is the pH range?"));
        questionBank.add(new Question("Biology", "What is the cell powerhouse?"));
    }

    private void updateQuestionTable() {
        String selectedTopic = (String) cboTopic.getSelectedItem();
        questionTableModel.setRowCount(0);
        for (Question q : questionBank) {
            if (q.topic.equals(selectedTopic)) {
                questionTableModel.addRow(new Object[] { q.text });
            }
        }
    }

    private void exportToDocx() {
        int selectedRow = testTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a test to export!");
            return;
        }

        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Test: " + tableModel.getValueAt(selectedRow, 1));
            titleRun.setBold(true);
            titleRun.setFontSize(16);

            XWPFParagraph topicPara = document.createParagraph();
            XWPFRun topicRun = topicPara.createRun();
            topicRun.setText("Topic: " + cboTopic.getSelectedItem());
            topicRun.setFontSize(14);

            for (int i = 0; i < questionTableModel.getRowCount(); i++) {
                XWPFParagraph qPara = document.createParagraph();
                XWPFRun qRun = qPara.createRun();
                qRun.setText((i + 1) + ". " + questionTableModel.getValueAt(i, 0));
            }

            try (FileOutputStream out = new FileOutputStream("TestExport.docx")) {
                document.write(out);
            }
            JOptionPane.showMessageDialog(this, "Exported to TestExport.docx successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error exporting to DOCX: " + ex.getMessage());
        }
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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

    private void updateDetailsPanel(int selectedRow) {
        // Keep existing functionality
        detailsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String testID = (String) tableModel.getValueAt(selectedRow, 0);
        String testName = (String) tableModel.getValueAt(selectedRow, 1);
        String numQuestions = (String) tableModel.getValueAt(selectedRow, 2);
        String creationDate = (String) tableModel.getValueAt(selectedRow, 3);

        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Test ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel(testID), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel("Test Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        detailsPanel.add(new JLabel(testName), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel("Number of Questions:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        detailsPanel.add(new JLabel(numQuestions), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        detailsPanel.add(new JLabel("Creation Date:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        detailsPanel.add(new JLabel(creationDate), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        detailsPanel.add(new JLabel("Chọn Môn Học:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        detailsPanel.add(cboTopic, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        detailsPanel.add(new JScrollPane(questionTable), gbc);

        detailsPanel.revalidate();
        detailsPanel.repaint();
        updateQuestionTable();
    }

    private void clearDetailsPanel() {
        detailsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailsPanel.add(new JLabel("Select a test to view details."), gbc);
        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void addTest() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Add Test", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField numQuestionsField = new JTextField(10);
        JButton okButton = createStyledButton("OK", new Color(52, 152, 219), Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Test Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        dialog.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Number of Questions:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialog.add(numQuestionsField, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        dialog.add(okButton, gbc);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void editTest() {
        JOptionPane.showMessageDialog(this, "Edit test functionality not implemented yet.");
    }
}

// Placeholder TestBUS class
class TestBUS {
    // Implement actual business logic here
}