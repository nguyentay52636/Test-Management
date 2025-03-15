// package org.example.GUI.Components.FormTest;

// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.FlowLayout;
// import java.awt.Font;
// import java.awt.GradientPaint;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.GridBagConstraints;
// import java.awt.GridBagLayout;
// import java.awt.Insets;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.util.List;

// import javax.swing.BorderFactory;
// import javax.swing.JButton;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.SwingConstants;

// import org.example.BUS.TestBUS;
// import org.example.BUS.TopicBUS;
// import org.example.DTO.SessionManager;
// import org.example.DTO.TestDTO;
// import org.example.DTO.TopicsDTO;

// public class TestForm extends JPanel {
// private JPanel contentPanel;

// public TestForm(JPanel contentPanel) {
// this.contentPanel = contentPanel;
// initComponents();
// }

// private void initComponents() {
// setLayout(new BorderLayout(0, 20));
// setBackground(new Color(240, 242, 245));
// setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

// // Header Panel with Gradient
// JPanel headerPanel = new JPanel() {
// @Override
// protected void paintComponent(Graphics g) {
// super.paintComponent(g);
// Graphics2D g2d = (Graphics2D) g;
// g2d.setPaint(new GradientPaint(0, 0, new Color(33, 150, 243), 0, getHeight(),
// new Color(25, 118, 210)));
// g2d.fillRect(0, 0, getWidth(), getHeight());
// }
// };
// headerPanel.setPreferredSize(new Dimension(0, 80));
// headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
// add(headerPanel, BorderLayout.NORTH);

// JLabel lblTitle = new JLabel("Chọn Chủ Đề Để Thi!");
// lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
// lblTitle.setForeground(Color.WHITE);
// headerPanel.add(lblTitle);

// // Content Wrapper Panel
// JPanel contentWrapper = new JPanel();
// contentWrapper.setOpaque(false);
// contentWrapper.setLayout(new BorderLayout());
// add(contentWrapper, BorderLayout.CENTER);

// // Topics List Panel
// JPanel topicsListPanel = new JPanel();
// topicsListPanel.setOpaque(false);
// topicsListPanel.setLayout(new GridBagLayout());
// GridBagConstraints gbc = new GridBagConstraints();
// gbc.insets = new Insets(10, 10, 10, 10);
// gbc.fill = GridBagConstraints.HORIZONTAL;
// gbc.weightx = 1.0;
// // display danh sach topic
// TopicBUS topicsBUS = new TopicBUS();
// List<TopicsDTO> topicsList = topicsBUS.getAllTopics();

// if (topicsList.isEmpty()) {
// JLabel noTopicsLabel = new JLabel("Không có chủ đề nào!",
// SwingConstants.CENTER);
// noTopicsLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
// noTopicsLabel.setForeground(new Color(220, 53, 69));
// contentWrapper.add(noTopicsLabel, BorderLayout.CENTER);
// } else {
// int columnCount = 0;
// for (TopicsDTO topic : topicsList) {
// JButton btnTopic = createStyledButton(topic.getTpTitle());
// btnTopic.addActionListener(e -> openExamUI(topic.getTopicID()));

// gbc.gridx = columnCount % 3;
// gbc.gridy = columnCount / 3;
// topicsListPanel.add(btnTopic, gbc);
// columnCount++;
// }

// JScrollPane scrollPane = new JScrollPane(topicsListPanel);
// scrollPane.setBorder(BorderFactory.createEmptyBorder());
// scrollPane.setOpaque(false);
// scrollPane.getViewport().setOpaque(false);
// contentWrapper.add(scrollPane, BorderLayout.CENTER);
// }
// }

// private JButton createStyledButton(String text) {
// JButton button = new JButton(text) {
// @Override
// protected void paintComponent(Graphics g) {
// Graphics2D g2d = (Graphics2D) g;
// g2d.setPaint(getBackground());
// g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
// super.paintComponent(g);
// }
// };
// button.setFont(new Font("Segoe UI", Font.BOLD, 18));
// button.setBackground(new Color(255, 255, 255));
// button.setForeground(new Color(33, 150, 243));
// button.setFocusPainted(false);
// button.setContentAreaFilled(false);
// button.setOpaque(false);
// button.setPreferredSize(new Dimension(220, 80));
// button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

// button.addMouseListener(new MouseAdapter() {
// @Override
// public void mouseEntered(MouseEvent e) {
// button.setBackground(new Color(245, 248, 255));
// button.setForeground(new Color(25, 118, 210));
// }

// @Override
// public void mouseExited(MouseEvent e) {
// button.setBackground(new Color(255, 255, 255));
// button.setForeground(new Color(33, 150, 243));
// }

// @Override
// public void mousePressed(MouseEvent e) {
// button.setBackground(new Color(230, 240, 255));
// }

// @Override
// public void mouseReleased(MouseEvent e) {
// button.setBackground(new Color(245, 248, 255));
// }
// });

// return button;
// }

// private void openExamUI(int topicID) {
// if (contentPanel != null) {
// TestBUS testBUS = new TestBUS();
// List<TestDTO> testList = testBUS.getTestsByTopicID(topicID);

// if (testList == null || testList.isEmpty()) {
// JOptionPane.showMessageDialog(this, "Không có bài thi nào cho chủ đề này!",
// "Thông báo",
// JOptionPane.INFORMATION_MESSAGE);
// return;
// }

// // Check if testStructure is properly initialized
// for (TestDTO test : testList) {
// if (test.getTestStructure() == null) {
// JOptionPane.showMessageDialog(this, "Cấu trúc bài thi chưa được khởi tạo!",
// "Lỗi",
// JOptionPane.ERROR_MESSAGE);
// return;
// }
// }

// TestUI examUI = new TestUI(contentPanel, topicID);
// contentPanel.removeAll();
// contentPanel.setLayout(new BorderLayout());
// contentPanel.add(examUI, BorderLayout.CENTER);
// contentPanel.revalidate();
// contentPanel.repaint();
// }
// }

// private int getCurrentUserID() {
// return SessionManager.getCurrentUser().getUserID();
// }
// }