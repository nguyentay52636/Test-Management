package org.example.GUI.Components.FormAccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;
import org.example.GUI.FormDialog.DialogUser.DialogAddAccount;
import org.example.Utils.importExcel;

/**
 * @author TayDev
 */
public class FormManagerAccount extends JPanel {
    UserBUS userBUS = new UserBUS();
    public importExcel importExcel;

    // InvoiceDetailBUS invoiceDetailsBUS = new InvoiceDetailBUS();

    public FormManagerAccount() {
        initComponents();
        refresh();
        disableButton();
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
    }

    private void button2(ActionEvent e) {
        // TODO add your code here
    }

    private void button3(ActionEvent e) {
        // TODO add your code here
    }

    private void textField1KeyTyped(KeyEvent e) {
        // TODO add your code here
    }

    // private void button4(ActionEvent e) {
    // FormDetailsInvoice detailsInvoice = new FormDetailsInvoice();
    // detailsInvoice.setVisible(true);
    // }

    private void initComponents() {
        // Initialize buttons
        btnAdd = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();
        btnImport = new JButton();
        btnExport = new JButton();

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Teddy
        this2 = new JPanel();
        this3 = new JPanel();
        panel1 = new JPanel();
        btnAction = new JPanel();
        panel2 = new JPanel();
        search = new JPanel();
        panel3 = new JPanel();
        comboBox1 = new JComboBox();
        textField1 = new JTextField();
        panel4 = new JPanel();
        textField2 = new JTextField();
        textField3 = new JTextField();
        panel5 = new JPanel();
        // btnxemChiTiet = new JButton();
        btnReload = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel6 = new JPanel();
        panel7 = new JPanel();
        txtUserId = new JTextField();
        txtUserName = new JTextField();
        txtPassword = new JTextField();
        txtPassword = new JTextField();
        txtFullName = new JTextField();
        panel8 = new JPanel();
        txtEmail = new JTextField();
        txtRole = new JTextField();
      

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
                . swing. border. EmptyBorder( 0, 0, 0, 0) , "", javax. swing
                . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
                Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new BorderLayout());

        //======== this2 ========
        {
            this2.setLayout(new BorderLayout());

            //======== this3 ========
            {
                this3.setLayout(new BorderLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new BorderLayout());

                    //======== btnAction ========
                    {
                        btnAction.setLayout(new BorderLayout());

                        //======== panel2 ========
                        {
                            panel2.setLayout(new FlowLayout());
                            ImageIcon iconThem = new ImageIcon(
                                getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png"));
                        ImageIcon iconXoa = new ImageIcon(
                                getClass().getResource("/org/example/GUI/resources/images/icons8_delete_forever_30px_1.png"));
                        ImageIcon IconSua = new ImageIcon(
                                getClass().getResource("/org/example/GUI/resources/images/icons8_wrench_30px.png"));
                        ImageIcon exportIcon = new ImageIcon(
                                getClass().getResource("/org/example/GUI/resources/images/icons8_ms_excel_30px.png"));
                        ImageIcon importIcon = new ImageIcon(
                                getClass().getResource("/org/example/GUI/resources/images/icons8_ms_excel_30px.png"));
                    
                                btnAdd.setIcon(iconThem);
                                btnDelete.setIcon(iconXoa);
                                btnEdit.setIcon(IconSua);
                                btnImport.setIcon(importIcon);
                                btnExport.setIcon(exportIcon);
                            btnAdd.setText("Th\u00eam");
                            btnAdd.setPreferredSize(new Dimension(144, 43));
                            panel2.add(btnAdd);
            
                            // ---- button2 ----
                            btnDelete.setText("X\u00f3a");
                            btnDelete.setPreferredSize(new Dimension(144, 43));
                            panel2.add(btnDelete);
            
                            // ---- button3 ----
                            btnEdit.setText("S\u1eeda");
                            btnEdit.setPreferredSize(new Dimension(144, 43));
                            panel2.add(btnEdit);

                            btnImport.setText("Nh\u1eadp Excel");
                            btnImport.setPreferredSize(new Dimension(144, 43));
                            panel2.add(btnImport);
                            // ---- button4 ----
                            btnExport.setText("Xu\u1ea5t Excel");
                            btnExport.setPreferredSize(new Dimension(144, 43));
                            panel2.add(btnExport);
            
                            // ---- button5 ----
                           

        
                          
                     

                            //
                            //
                        }
                        btnAction.add(panel2, BorderLayout.NORTH);

                        //======== search ========
                        {
                            search.setMinimumSize(new Dimension(1200, 76));
                            search.setLayout(new FlowLayout());

                            //======== panel3 ========
                            {
                                panel3.setBorder(new TitledBorder(null, "T\u00ecm ki\u1ebfm:", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
//                                panel3.setMinimumSize(new Dimension(500, 49));
//                                panel3.setPreferredSize(new Dimension(220, 49));
                                panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
                                String[] items = { "Tất cả", "Mã người dùng", "Tên người dùng", "Mật khẩu", "Họ và tên", "Email" };
                                for (String item : items) {
                                    comboBox1.addItem(item);
                                }
                                comboBox1.setPreferredSize(new Dimension(130, 40));
                                panel3.add(comboBox1);

                                //---- textField1 ----
                                textField1.setPreferredSize(new Dimension(144, 55));
                                textField1.addKeyListener(new KeyAdapter() {
                                    @Override
                                    public void keyTyped(KeyEvent e) {
                                        textField1KeyTyped(e);
                                    }
                                });
                                panel3.add(textField1);
                            }
                            search.add(panel3);

                            //======== panel4 ========
                            {
                                panel4.setBorder(new TitledBorder(null, "Ng\u00e0y l\u1eadp:", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
//                                panel4.setMinimumSize(new Dimension(220, 66));
//                                panel4.setPreferredSize(new Dimension(200, 62));
                                panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

                                //---- textField2 ----
                                textField2.setBorder(new TitledBorder(null, "T\u1eeb:", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                                textField2.setPreferredSize(new Dimension(80, 55));
                                panel4.add(textField2);

                                //---- textField3 ----
                                textField3.setBorder(new TitledBorder(null, "\u0110\u1ebfn:", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                                textField3.setPreferredSize(new Dimension(80, 55));
                                panel4.add(textField3);
                            }
                            search.add(panel4);

                            //======== panel5 ========
                            {
                                panel5.setPreferredSize(new Dimension(300, 70));
                                panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

                                // //---- button4 ----
                                // btnxemChiTiet.setText("Xem chi ti\u1ebft");
                                // btnxemChiTiet.setPreferredSize(new Dimension(100, 100));
                                // btnxemChiTiet.setIcon(new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_show_property_30px.png")));
                                // btnxemChiTiet.setMaximumSize(new Dimension(160, 60));
                                // btnxemChiTiet.setIconTextGap(3);
                                // btnxemChiTiet.setMinimumSize(new Dimension(100, 40));
                                // btnxemChiTiet.addActionListener(e -> {
                                //     //			button4(e);
                                //     //			button4(e);
                                // });
                                // panel5.add(btnxemChiTiet);

                                //---- button5 ----
                                btnReload.setText("L\u00e0m m\u1edbi");
                                btnReload.setIcon(new ImageIcon(getClass().getResource("/org/example/GUI/resources/images/icons8_data_backup_30px.png")));
                                btnReload.setPreferredSize(new Dimension(100, 50));
                                btnReload.setMaximumSize(new Dimension(119, 60));
                                panel5.add(btnReload);
                            }
                            search.add(panel5);
                        }
                        btnAction.add(search, BorderLayout.CENTER);
                    }
                    panel1.add(btnAction, BorderLayout.NORTH);

                    //======== scrollPane1 ========
                    {    Font font = new Font("Segoe UI", 0, 16);
                        Font fontHeader = new Font("Segoe UI", Font.BOLD, 16);

                        // set font for the table
                        table1.setFont(font);
                        table1.getTableHeader().setFont(fontHeader);
                        table1.setRowHeight(30);
                        JTableHeader header = table1.getTableHeader();
                      DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
                        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
                        TableColumnModel columnModel = table1.getColumnModel();
                        for (int i = 0; i < columnModel.getColumnCount(); i++) {
                            columnModel.getColumn(i).setPreferredWidth(150);
                        }

                        ListSelectionModel selectionModel = table1.getSelectionModel();

                        selectionModel.addListSelectionListener(new ListSelectionListener() {
                            @Override
                            public void valueChanged(ListSelectionEvent e) {
                                if (!e.getValueIsAdjusting()) {
                                    // Lấy chỉ số hàng được chọn
                                    int selectedRow = table1.getSelectedRow();
                                    if (selectedRow != -1) {String userName = (String) table1.getValueAt(selectedRow, 1);
                                        displayInfo(userName);
                                        System.out.println("Selected row: " + selectedRow);
                                    }
                                }
                            }
                        });
                        // btnxemChiTiet.addActionListener(new ActionListener() {
                        //     @Override
                        //     public void actionPerformed(ActionEvent e) {
                        //         int selectedRow = table1.getSelectedRow();
                        //         if (selectedRow != -1) {
                        //             String maHD = table1.getValueAt(selectedRow, 1).toString();
                        //             System.out.println("Selected MaHD: " + maHD);

                        //             JFrame frame = new JFrame("Chi tiết hóa đơn");
                        //             FormInvoiceDetails formInvoiceDetails = new FormInvoiceDetails(maHD);
                        //             frame.add(formInvoiceDetails);
                        //             frame.setSize(800, 600);
                        //             frame.setLocationRelativeTo(null);
                        //             frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        //             frame.setVisible(true);
                        //         } else {
                        //             JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem chi tiết");
                        //         }
                        //     }
                        // });

                        //---- table1 ----
                        


                        table1.setFocusable(false);
                        table1.setAutoCreateRowSorter(true);
                        scrollPane1.setViewportView(table1);


                    }
                    panel1.add(scrollPane1, BorderLayout.CENTER);

                    //======== panel6 ========
                    {
                        panel6.setLayout(new GridLayout(3, 1));

                        //======== panel7 ========
                        {
                            panel7.setLayout(new FlowLayout());

                            //---- textField5 ----
                            txtUserId.setBorder(new TitledBorder(null, "Mã tài khoản ", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                            txtUserId.setPreferredSize(new Dimension(200, 55));
                            panel7.add(txtUserId);

                            //---- textField6 ----
                            txtUserName.setBorder(new TitledBorder(null, "Tên đăng nhập  ", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                            txtUserName.setPreferredSize(new Dimension(200, 55));
                            panel7.add(txtUserName);

                            //---- textField7 ----
                            txtPassword.setBorder(new TitledBorder(null, "Mật khẩu", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                            txtPassword.setPreferredSize(new Dimension(200, 55));
                            panel7.add(txtPassword);

                            //---- textField8 ----
                            txtFullName.setBorder(new TitledBorder(null, "Họ và tên", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                            txtFullName.setPreferredSize(new Dimension(200, 55));
                            panel7.add(txtFullName);
                        }
                        panel6.add(panel7);

                        //======== panel8 ========
                        {
                            panel8.setLayout(new FlowLayout());

                            //---- textField9 ----
                            txtEmail.setBorder(new TitledBorder(null, "Emai", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                            txtEmail.setPreferredSize(new Dimension(200, 55));
                            panel8.add(txtEmail);

                            //---- textField10 ----
                            txtRole.setBorder(new TitledBorder(null, "Quyền", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.black));
                            txtRole.setPreferredSize(new Dimension(200, 55));
                            panel8.add(txtRole);

                            //---- textField11 ----
                        }
                        panel6.add(panel8);
                    }
                    panel1.add(panel6, BorderLayout.SOUTH);
                }
                this3.add(panel1, BorderLayout.CENTER);
            }
            this2.add(this3, BorderLayout.CENTER);
        }
        add(this2, BorderLayout.CENTER);
    

        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                performSearch();
            }

            public void performSearch() {
                String value = textField1.getText();
                String type = (String) comboBox1.getSelectedItem();
                ArrayList<UsersDTO> result = userBUS.search(value, type);
                setDataToTable(result);
            }
        });
    
        btnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                comboBox1.setSelectedItem("Tất cả ");
                textField1.setText("");
            }

        });
      btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAddAccount dialog = new DialogAddAccount("Thêm", "");
                dialog.setAccountAddedListener(new AccountAddedListener() {
                    @Override
                    public void onAccountAdded(UsersDTO newAccount) {
                        refresh();
                    }
                });
                dialog.setVisible(true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa tài khoản này không?",
                            "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        int userID = Integer.parseInt(txtUserId.getText());
                        userBUS.deleteUser(userID);
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản " + userID + " thành công");
                        // chấp vá
                           refresh();
                           refresh();
                    }

              
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản cần xóa");
                }
            }
        });
         btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản");
                    return;
                }
                String username = (String) table1.getValueAt(selectedRow,1);
                    // System.out.println(username);
        
                DialogAddAccount account = new DialogAddAccount("Sửa", username);
                account.setAccountAddedListener(new AccountAddedListener() {
                    @Override
                    public void onAccountAdded(UsersDTO newAccount) {
                        // Giữ nguyên userID khi tạo đối tượng mới
                        UsersDTO updatedAccount = new UsersDTO(newAccount.getUserID(), 
                                                               newAccount.getUserName(), 
                                                               newAccount.getUserEmail(),
                                                               newAccount.getUserPassword(), 
                                                               newAccount.getUserFullName(), 
                                                               newAccount.getIsAdmin());
                
                      userBUS.updateUser(updatedAccount);
                      refresh();
                    }
                });
                
   
                account.setVisible(true);
            }
        });
        
    

        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setDialogTitle("Chọn file Excel");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));
        
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Read users from Excel
                        ArrayList<UsersDTO> importedUsers = importExcel.readUsersExcel(selectedFile.getAbsolutePath());
                        if (importedUsers.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Không có dữ liệu người dùng trong file Excel!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
        
                        UserBUS userBUS = new UserBUS();
                        List<UsersDTO> existingUsers = userBUS.getListAccount();
                        List<UsersDTO> usersToAdd = new ArrayList<>();
                        StringBuilder duplicateMessage = new StringBuilder();
                        StringBuilder successMessage = new StringBuilder();
                        int duplicateCount = 0;
                        int successCount = 0;
        
                        // Check for duplicates and prepare users to add
                        for (UsersDTO importedUser : importedUsers) {
                            boolean isDuplicate = false;
                            for (UsersDTO existingUser : existingUsers) {
                                if (existingUser.getUserName().equals(importedUser.getUserName()) ||
                                    existingUser.getUserEmail().equals(importedUser.getUserEmail())) {
                                    isDuplicate = true;
                                    duplicateCount++;
                                    duplicateMessage.append("Người dùng ").append(importedUser.getUserName())
                                            .append(" (Email: ").append(importedUser.getUserEmail())
                                            .append(") đã tồn tại.\n");
                                    break;
                                }
                            }
        
                            if (!isDuplicate) {
                                // Assign a new userID (find the max ID and increment)
                                int maxId = existingUsers.stream()
                                        .mapToInt(UsersDTO::getUserID)
                                        .max()
                                        .orElse(0);
                                importedUser.setUserID(maxId + 1);
                                usersToAdd.add(importedUser);
                            }
                        }
        
                        // Add non-duplicate users to the database
                        for (UsersDTO userToAdd : usersToAdd) {
                            if (userBUS.insertUser(userToAdd)) {
                                successCount++;
                                successMessage.append("Đã thêm người dùng ").append(userToAdd.getUserName())
                                        .append(" (Email: ").append(userToAdd.getUserEmail()).append(").\n");
                                existingUsers.add(userToAdd); // Update the in-memory list
                            } else {
                                JOptionPane.showMessageDialog(null, "Lỗi khi thêm người dùng " + userToAdd.getUserName(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
        
                        // Display results
                        StringBuilder finalMessage = new StringBuilder();
                        if (duplicateCount > 0) {
                            finalMessage.append("Có ").append(duplicateCount).append(" người dùng đã tồn tại:\n")
                                    .append(duplicateMessage.toString()).append("\n");
                        }
                        if (successCount > 0) {
                            finalMessage.append("Đã thêm thành công ").append(successCount).append(" người dùng:\n")
                                    .append(successMessage.toString());
                        }
                        if (duplicateCount == 0 && successCount == 0) {
                            finalMessage.append("Không có người dùng nào được thêm!");
                        }
        
                        JOptionPane.showMessageDialog(null, finalMessage.toString(), "Kết quả nhập dữ liệu", 
                                (successCount > 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE));
        
                        // Update the table with the current data
                        setDataToTable(new ArrayList<>(existingUsers));
        
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi nhập dữ liệu từ file Excel: " + e.getMessage(), 
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
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
        System.out.println("UserName cần tìm: " + userName);

        try {
            for (UsersDTO user : userBUS.getListAccount()) {
                // System.out.println("So sánh với: " + user.getUserName());

                if (user.getUserName().trim().equalsIgnoreCase(userName.trim())) { 

                    txtUserId.setText(String.valueOf(user.getUserID()));
                    txtUserName.setText(user.getUserName());
                    txtPassword.setText(user.getUserPassword());
                    txtFullName.setText(user.getUserFullName());
                    txtEmail.setText(user.getUserEmail());
                    txtRole.setText("Quyền" + "-" + (user.getIsAdmin() ? "Admin" : "User"));

                    // System.out.println("Dữ liệu đã được set vào TextField");
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

    // Căn giữa nội dung trong các cột
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Căn giữa

    // Áp dụng căn giữa cho tất cả các cột
    for (int i = 0; i < table1.getColumnCount(); i++) {
        table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
}

    public void checkTable() {
        // Lấy số hàng và số cột của bảng
        int rowCount = table1.getRowCount();
        int columnCount = table1.getColumnCount();

        // In ra số hàng và số cột của bảng
        System.out.println("Number of rows: " + rowCount);
        System.out.println("Number of columns: " + columnCount);

        // Duyệt qua từng ô của bảng và in ra giá trị của từng ô
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

    private JButton btnAdd ; 
    private JButton btnEdit ;
    private JButton btnDelete ;
    private JPanel this2;
    private JPanel this3;
    private JPanel panel1;
    private JPanel btnAction;
    private JPanel panel2;
    private JButton   btnImport ;
    private JButton btnExport;
    private JButton button3;
    private JPanel search;
    private JPanel panel3;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPanel panel4;
    private JTextField textField2;
    private JTextField textField3;
    private JPanel panel5;
    private JButton btnxemChiTiet;
    private JButton btnReload;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel6;
    private JPanel panel7;
    private JTextField txtUserId ;
    private JTextField txtUserName;
    private JTextField txtPassword;
    private JTextField txtFullName ;
    private JPanel panel8;
    private JTextField txtEmail;
    private JTextField txtRole;


}
