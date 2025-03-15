package org.example.GUI.Components.FormDashBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.example.BUS.ResultBUS;
import org.example.BUS.TopicBUS; // Added for topic fetching
import org.example.ConnectDB.UtilsJDBC;
import org.example.DTO.ResultDTO;
import org.example.DTO.TopicsDTO; // Added for topic data
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class FormDashboard extends JPanel {
    private ResultBUS resultBUS;
    private TopicBUS topicBUS; // Added TopicBUS instance
    private JLabel lblParticipants, lblPasses, lblFailures;
    private ChartPanel chartPanel;
    private JTable participantTable;
    private DefaultTableModel tableModel;
    private JButton btnShowAll, btnShowPassed, btnShowFailed;
    private List<ResultDTO> currentResults;
    private JSplitPane splitPane;
    private JComboBox<String> headerComboBox;

    public FormDashboard() {
        resultBUS = new ResultBUS();
        topicBUS = new TopicBUS(); // Initialize TopicBUS
        currentResults = new ArrayList<>();
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initializeUI();
        loadStatisticsWithFilter("All");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> UtilsJDBC.closeConnection()));
    }

    private void initializeUI() {
        setLayout(new BorderLayout(0, 15));

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
        headerPanel.setPreferredSize(new Dimension(0, 70));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel("Thống Kê Kết Quả Thi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.WEST);

        headerComboBox = new JComboBox<>();
        headerComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        headerComboBox.setPreferredSize(new Dimension(200, 30));
        headerComboBox.setBackground(Color.WHITE);
        headerComboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        loadTopicsIntoComboBox(); // Load topics into JComboBox
        headerPanel.add(headerComboBox, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.4);
        splitPane.setDividerSize(10);
        splitPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel leftPanel = new JPanel(new BorderLayout(0, 15));
        leftPanel.setOpaque(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Thống Kê"));

        lblParticipants = createStatLabel("Tổng số thí sinh: 0");
        lblPasses = createStatLabel("Số thí sinh đạt (>=5.00): 0");
        lblFailures = createStatLabel("Số thí sinh trượt: 0");

        statsPanel.add(lblParticipants);
        statsPanel.add(lblPasses);
        statsPanel.add(lblFailures);
        leftPanel.add(statsPanel, BorderLayout.NORTH);

        chartPanel = createPieChart(0, 0);
        JPanel chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBackground(Color.WHITE);
        chartContainer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Biểu Đồ"));
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        leftPanel.add(chartContainer, BorderLayout.CENTER);

        splitPane.setLeftComponent(leftPanel);

        JPanel rightPanel = new JPanel(new BorderLayout(0, 15));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        filterPanel.setOpaque(false);
        btnShowAll = createStyledButton("Tất cả", new Color(52, 152, 219), Color.WHITE);
        btnShowPassed = createStyledButton("Đạt", new Color(46, 204, 113), Color.WHITE);
        btnShowFailed = createStyledButton("Trượt", new Color(217, 83, 79), Color.WHITE);

        btnShowAll.addActionListener(e -> loadStatisticsWithFilter("All"));
        btnShowPassed.addActionListener(e -> loadStatisticsWithFilter("Passed"));
        btnShowFailed.addActionListener(e -> loadStatisticsWithFilter("Failed"));

        filterPanel.add(btnShowAll);
        filterPanel.add(btnShowPassed);
        filterPanel.add(btnShowFailed);
        rightPanel.add(filterPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[] { "Tên Thí Sinh", "Điểm Cao Nhất", "Kết Quả" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        participantTable = new JTable(tableModel);
        participantTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        participantTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        participantTable.setRowHeight(30);
        participantTable.setGridColor(new Color(220, 220, 220));
        participantTable.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        participantTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        participantTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane tableScrollPane = new JScrollPane(participantTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1), "Danh Sách Thí Sinh"));
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);

        splitPane.setRightComponent(rightPanel);
        add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        buttonPanel.setOpaque(false);
        JButton btnRefresh = createStyledButton("Làm mới", new Color(52, 152, 219), Color.WHITE);
        btnRefresh.addActionListener(e -> loadStatisticsWithFilter("All"));
        buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(new Color(44, 62, 80));
        label.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        return label;
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
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
        });
        return button;
    }

    private ChartPanel createPieChart(int passes, int failures) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int total = passes + failures;
        dataset.setValue("Đạt (>=5.00)", total > 0 ? passes : 0);
        dataset.setValue("Trượt", total > 0 ? failures : 0);

        JFreeChart chart = ChartFactory.createPieChart(
                "Phân Bố Kết Quả",
                dataset,
                true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Đạt (>=5.00)", new Color(46, 204, 113));
        plot.setSectionPaint("Trượt", new Color(217, 83, 79));
        plot.setBackgroundPaint(new Color(248, 249, 250));
        plot.setOutlinePaint(new Color(173, 181, 189));
        plot.setLabelFont(new Font("Segoe UI", Font.PLAIN, 14));
        plot.setLabelBackgroundPaint(new Color(233, 236, 239));
        plot.setSimpleLabels(true);
        plot.setInteriorGap(0.02);
        plot.setNoDataMessage("Không có dữ liệu");

        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    private void setDataToTable(Map<String, Float> highestScores, String filter) {
        tableModel.setRowCount(0);

        if (highestScores == null || highestScores.isEmpty()) {
            tableModel.addRow(new Object[] { "Không có dữ liệu", "", "" });
            System.out.println("No data in highestScores");
            return;
        }

        for (Map.Entry<String, Float> entry : highestScores.entrySet()) {
            String userName = entry.getKey() != null ? entry.getKey() : "Unknown";
            float score = entry.getValue();
            boolean passed = score >= 5.00;
            String resultStatus = passed ? "Đạt" : "Trượt";

            boolean shouldAdd = false;
            switch (filter) {
                case "All":
                    shouldAdd = true;
                    break;
                case "Passed":
                    shouldAdd = passed;
                    break;
                case "Failed":
                    shouldAdd = !passed;
                    break;
            }

            if (shouldAdd) {
                tableModel.addRow(new Object[] { userName, String.format("%.2f", score), resultStatus });
                System.out.println("Added to table: " + userName + ", " + score + ", " + resultStatus);
            }
        }
        participantTable.revalidate();
        participantTable.repaint();
    }

    private void loadTopicsIntoComboBox() {
        headerComboBox.removeAllItems();
        headerComboBox.addItem("All"); // Add "All" as the default option
        List<TopicsDTO> topics = topicBUS.getAllTopics(); // Fetch topics from TopicBUS
        if (topics != null && !topics.isEmpty()) {
            for (TopicsDTO topic : topics) {
                headerComboBox.addItem(topic.getTpTitle()); // Add each topic title
            }
        } else {
            System.out.println("No topics loaded from TopicBUS");
        }
    }

    private void loadStatisticsWithFilter(String filter) {
        List<ResultDTO> allResults = resultBUS.getResult();
        if (allResults == null) {
            allResults = new ArrayList<>();
            System.out.println("resultBUS.getResult() returned null");
        }
        System.out.println("Raw results size: " + allResults.size());
        currentResults = new ArrayList<>(allResults);

        Map<String, Float> highestScores = new HashMap<>();
        for (ResultDTO result : currentResults) {
            String userName = result.getUserFullName();
            float score = result.getRsMark();
            highestScores.merge(userName, score, Float::max);
            System.out.println("Processing: " + userName + ", Score: " + score);
        }
        System.out.println("Highest scores: " + highestScores);

        int totalParticipants = highestScores.size();
        int totalPasses = 0;
        int totalFailures = 0;

        for (float score : highestScores.values()) {
            if (score >= 5.00) {
                totalPasses++;
            } else {
                totalFailures++;
            }
        }

        lblParticipants.setText("Tổng số thí sinh: " + totalParticipants);
        lblPasses.setText("Số thí sinh đạt (>=5.00): " + totalPasses);
        lblFailures.setText("Số thí sinh trượt: " + totalFailures);

        chartPanel.setChart(createPieChart(totalPasses, totalFailures).getChart());

        setDataToTable(highestScores, filter);

        int totalWidth = splitPane.getWidth();
        if (totalWidth > 0) {
            splitPane.setDividerLocation((int) (totalWidth * 0.4));
        } else {
            splitPane.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {
                    splitPane.setDividerLocation((int) (splitPane.getWidth() * 0.4));
                    splitPane.removeComponentListener(this);
                }
            });
        }
    }

    public List<ResultDTO> getCurrentResults() {
        return new ArrayList<>(currentResults);
    }

    public static class Main {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Dashboard");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200, 800);
                frame.setLocationRelativeTo(null);
                frame.setContentPane(new FormDashboard());
                frame.setVisible(true);
            });
        }
    }
}