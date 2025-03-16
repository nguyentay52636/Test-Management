package org.example.GUI.Components.FormAccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.TitledBorder;
import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;
import org.example.GUI.FormDialog.DialogUser.DialogAddAccount;
import org.example.Utils.importExcel;

public class FormManagerAccount extends JPanel {
    UserBUS userBUS = new UserBUS();
    public importExcel importExcel;

    private JButton btnAdd, btnEdit, btnDelete, btnImport, btnExport, btnReload;
    private JPanel this2, this3, panel1, btnAction, panel2, search, panel3, panel4, panel5, panel6, panel7, panel8;
    private JComboBox<String> comboBox1;
    private JTextField textField1, textField2, textField3, txtUserId, txtUserName, txtPassword, txtFullName, txtEmail, txtRole;
    private JScrollPane scrollPane1;
    private JTable table1;

    public FormManagerAccount() {
        initComponents();
        refresh();
        disableButton();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 242, 245)); // Nền nhẹ nhàng, chuyên nghiệp

        // Header Panel với gradient
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), 0, new Color(41, 128, 185));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JLabel titleLabel = new JLabel("Quản Lý Tài Khoản");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        this2 = new JPanel(new BorderLayout(10, 10));
        this2.setOpaque(false);
        this2.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(this2, BorderLayout.CENTER);

        // Button Action Panel
        btnAction = new JPanel(new BorderLayout());
        btnAction.setOpaque(false);
        this2.add(btnAction, BorderLayout.NORTH);

        // Button Panel
        panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel2.setOpaque(false);
        btnAction.add(panel2, BorderLayout.NORTH);

        btnAdd = createStyledButton("Thêm", new Color(46, 204, 113), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png")));
        btnDelete = createStyledButton("Xóa", new Color(231, 76, 60), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_delete_forever_30px_1.png")));
        btnEdit = createStyledButton("Sửa", new Color(52, 152, 219), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_wrench_30px.png")));
        btnImport = createStyledButton("Nhập Excel", new Color(155, 89, 182), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_ms_excel_30px.png")));
        btnExport = createStyledButton("Xuất Excel", new Color(241, 196, 15), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_ms_excel_30px.png")));

        panel2.add(btnAdd);
        panel2.add(btnDelete);
        panel2.add(btnEdit);
        panel2.add(btnImport);
        panel2.add(btnExport);

        // Search Panel
        search = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        search.setOpaque(false);
        btnAction.add(search, BorderLayout.CENTER);

        panel3 = new JPanel();
        panel3.setBackground(Color.WHITE);
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)), "Tìm kiếm", 
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 14)));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        String[] items = {"Tất cả", "Mã người dùng", "Tên người dùng", "Mật khẩu", "Họ và tên", "Email"};
        comboBox1 = new JComboBox<>(items);
        comboBox1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox1.setPreferredSize(new Dimension(150, 40));
        textField1 = createStyledTextField(200, 40);
        panel3.add(comboBox1);
        panel3.add(Box.createHorizontalStrut(10));
        panel3.add(textField1);
        search.add(panel3);

        panel4 = new JPanel();
        panel4.setBackground(Color.WHITE);
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(189, 195, 199)), "Ngày lập", 
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 14)));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        textField2 = createStyledTextField(100, 40, "Từ");
        textField3 = createStyledTextField(100, 40, "Đến");
        panel4.add(textField2);
        panel4.add(Box.createHorizontalStrut(10));
        panel4.add(textField3);
        search.add(panel4);

        panel5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel5.setOpaque(false);
        btnReload = createStyledButton("Làm mới", new Color(52, 152, 219), 
                new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_data_backup_30px.png")));
        panel5.add(btnReload);
        search.add(panel5);

        // Table Panel
        panel1 = new JPanel(new BorderLayout());
        panel1.setOpaque(false);
        this2.add(panel1, BorderLayout.CENTER);

        table1 = new JTable();
        table1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table1.setRowHeight(35);
        table1.setGridColor(new Color(233, 236, 239));
        table1.setShowGrid(true);
        JTableHeader header = table1.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(52, 152, 219));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 40));
        scrollPane1 = new JScrollPane(table1);
        scrollPane1.setBorder(BorderFactory.createLineBorder(new Color(220, 223, 230)));
        panel1.add(scrollPane1, BorderLayout.CENTER);

        // Info Panel
        panel6 = new JPanel(new GridLayout(2, 1, 10, 10));
        panel6.setOpaque(false);
        panel6.setBorder(new EmptyBorder(15, 0, 0, 0));
        panel1.add(panel6, BorderLayout.SOUTH);

        panel7 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panel7.setOpaque(false);
        txtUserId = createStyledTextField(180, 50, "Mã tài khoản");
        txtUserName = createStyledTextField(180, 50, "Tên đăng nhập");
        txtPassword = createStyledTextField(180, 50, "Mật khẩu");
        txtFullName = createStyledTextField(180, 50, "Họ và tên");
        panel7.add(txtUserId);
        panel7.add(txtUserName);
        panel7.add(txtPassword);
        panel7.add(txtFullName);
        panel6.add(panel7);

        panel8 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panel8.setOpaque(false);
        txtEmail = createStyledTextField(180, 50, "Email");
        txtRole = createStyledTextField(180, 50, "Quyền");
        panel8.add(txtEmail);
        panel8.add(txtRole);
        panel6.add(panel8);

        // Gắn các sự kiện
        attachEventListeners();
    }

    private JButton createStyledButton(String text, Color bgColor, ImageIcon icon) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(140, 40));
        button.setBorder(new EmptyBorder(5, 10, 5, 10));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

    private JTextField createStyledTextField(int width, int height) {
        return createStyledTextField(width, height, null);
    }

    private JTextField createStyledTextField(int width, int height, String title) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(width, height));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                new EmptyBorder(5, 10, 5, 10)));
        if (title != null) {
            textField.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199)), title,
                    TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.PLAIN, 12)));
        }
        return textField;
    }

    private void attachEventListeners() {
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { performSearch(); }
            @Override
            public void removeUpdate(DocumentEvent e) { performSearch(); }
            @Override
            public void changedUpdate(DocumentEvent e) { performSearch(); }
            private void performSearch() {
                String value = textField1.getText();
                String type = (String) comboBox1.getSelectedItem();
                ArrayList<UsersDTO> result = userBUS.search(value, type);
                setDataToTable(result);
            }
        });

        btnReload.addActionListener(e -> {
            comboBox1.setSelectedItem("Tất cả");
            textField1.setText("");
            refresh();
        });

        btnAdd.addActionListener(e -> {
            DialogAddAccount dialog = new DialogAddAccount("Thêm", "");
            dialog.setAccountAddedListener(newAccount -> refresh());
            dialog.setVisible(true);
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow != -1) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này không?", 
                        "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    int userID = Integer.parseInt(txtUserId.getText());
                    userBUS.deleteUser(userID);
                    JOptionPane.showMessageDialog(null, "Xóa tài khoản " + userID + " thành công");
                    refresh();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xóa");
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản");
                return;
            }
            String username = (String) table1.getValueAt(selectedRow, 1);
            DialogAddAccount account = new DialogAddAccount("Sửa", username);
            account.setAccountAddedListener(newAccount -> {
                UsersDTO updatedAccount = new UsersDTO(newAccount.getUserID(), newAccount.getUserName(), 
                        newAccount.getUserEmail(), newAccount.getUserPassword(), newAccount.getUserFullName(), 
                        newAccount.getIsAdmin());
                userBUS.updateUser(updatedAccount);
                refresh();
            });
            account.setVisible(true);
        });

        btnImport.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("Chọn file Excel");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    ArrayList<UsersDTO> importedUsers = importExcel.readUsersExcel(selectedFile.getAbsolutePath());
                    if (importedUsers.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không có dữ liệu người dùng trong file Excel!", 
                                "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    List<UsersDTO> existingUsers = userBUS.getListAccount();
                    List<UsersDTO> usersToAdd = new ArrayList<>();
                    StringBuilder duplicateMessage = new StringBuilder();
                    StringBuilder successMessage = new StringBuilder();
                    int duplicateCount = 0, successCount = 0;

                    for (UsersDTO importedUser : importedUsers) {
                        boolean isDuplicate = false;
                        for (UsersDTO existingUser : existingUsers) {
                            if (existingUser.getUserName().equals(importedUser.getUserName()) ||
                                    existingUser.getUserEmail().equals(importedUser.getUserEmail())) {
                                isDuplicate = true;
                                duplicateCount++;
                                duplicateMessage.append("Người dùng ").append(importedUser.getUserName())
                                        .append(" (Email: ").append(importedUser.getUserEmail()).append(") đã tồn tại.\n");
                                break;
                            }
                        }
                        if (!isDuplicate) {
                            int maxId = existingUsers.stream().mapToInt(UsersDTO::getUserID).max().orElse(0);
                            importedUser.setUserID(maxId + 1);
                            usersToAdd.add(importedUser);
                        }
                    }

                    for (UsersDTO userToAdd : usersToAdd) {
                        if (userBUS.insertUser(userToAdd)) {
                            successCount++;
                            successMessage.append("Đã thêm người dùng ").append(userToAdd.getUserName())
                                    .append(" (Email: ").append(userToAdd.getUserEmail()).append(").\n");
                            existingUsers.add(userToAdd);
                        } else {
                            JOptionPane.showMessageDialog(null, "Lỗi khi thêm người dùng " + userToAdd.getUserName(), 
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    StringBuilder finalMessage = new StringBuilder();
                    if (duplicateCount > 0) finalMessage.append("Có ").append(duplicateCount).append(" người dùng đã tồn tại:\n").append(duplicateMessage).append("\n");
                    if (successCount > 0) finalMessage.append("Đã thêm thành công ").append(successCount).append(" người dùng:\n").append(successMessage);
                    if (duplicateCount == 0 && successCount == 0) finalMessage.append("Không có người dùng nào được thêm!");
                    JOptionPane.showMessageDialog(null, finalMessage.toString(), "Kết quả nhập dữ liệu", 
                            successCount > 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
                    setDataToTable(new ArrayList<>(existingUsers));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file Excel: " + ex.getMessage(), 
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        ListSelectionModel selectionModel = table1.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table1.getSelectedRow();
                    if (selectedRow != -1) {
                        String userName = (String) table1.getValueAt(selectedRow, 1);
                        displayInfo(userName);
                    }
                }
            }
        });
    }

    public void disableButton() {
        btnExport.setEnabled(false);
    }

    public void refresh() {
        userBUS.getUserAll();
        setDataToTable(userBUS.getListAccount());
    }

    private void displayInfo(String userName) {
        if (userName != null) {
            try {
                for (UsersDTO user : userBUS.getListAccount()) {
                    if (user.getUserName().trim().equalsIgnoreCase(userName.trim())) {
                        txtUserId.setText(String.valueOf(user.getUserID()));
                        txtUserName.setText(user.getUserName());
                        txtPassword.setText(user.getUserPassword());
                        txtFullName.setText(user.getUserFullName());
                        txtEmail.setText(user.getUserEmail());
                        txtRole.setText("Quyền" + "-" + (user.getIsAdmin() ? "Admin" : "User"));
                        return;
                    }
                }
                System.out.println("Không tìm thấy user!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi hiển thị thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setDataToTable(ArrayList<UsersDTO> data) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã người dùng");
        model.addColumn("Tên đăng nhập");
        model.addColumn("Mật khẩu");
        model.addColumn("Họ và tên");
        model.addColumn("Email");
        model.addColumn("Quyền admin");

        for (UsersDTO user : data) {
            model.addRow(new Object[] {
                user.getUserID(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserFullName(),
                user.getUserEmail(),
                user.getIsAdmin() ? "Admin" : "User"
            });
        }

        table1.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void checkTable() {
        int rowCount = table1.getRowCount();
        int columnCount = table1.getColumnCount();
        System.out.println("Number of rows: " + rowCount);
        System.out.println("Number of columns: " + columnCount);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Object value = table1.getValueAt(i, j);
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    public interface AccountAddedListener {
        void onAccountAdded(UsersDTO newAccount);
    }
}