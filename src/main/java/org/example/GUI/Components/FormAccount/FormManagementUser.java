package org.example.GUI.Components.FormAccount;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.example.DTO.UsersDTO;
import org.example.GUI.FormDialog.DialogUser.DialogAddAccount;

//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import Layout.models.BackEnd.BUS.AccountBUS;
//import Layout.models.BackEnd.DTO.Account;

public class FormManagementUser extends JPanel {

    public FormManagementUser() {
        initComponents();

    }

    private void initComponents() {
        panel1 = new JPanel();
        panel2 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        panel3 = new JPanel();
        panel5 = new JPanel();
        paneltr = new JPanel();
        comboBox1 = new JComboBox();
        comboBox2 = new JComboBox();
        textField1 = new JTextField();
        button6 = new JButton();
        panel4 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        // ======== this ========
        setBorder(
                new javax.swing.border.CompoundBorder(
                        new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0), "",
                                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM,
                                new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12), java.awt.Color.red),
                        getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062order".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });
        setLayout(new BorderLayout());

        // ======== panel1 ========
        {
            panel1.setLayout(new GridLayout(3, 1));

            // ======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());

                // ---- button1 ----
                button1.setText("Th\u00eam");
                button1.setPreferredSize(new Dimension(144, 43));
                panel2.add(button1);

                // ---- button2 ----
                button2.setText("X\u00f3a");
                button2.setPreferredSize(new Dimension(144, 43));
                panel2.add(button2);

                // ---- button3 ----
                button3.setText("S\u1eeda");
                button3.setPreferredSize(new Dimension(144, 43));
                panel2.add(button3);

                // ---- button4 ----
                button4.setText("Xu\u1ea5t Excel");
                button4.setPreferredSize(new Dimension(144, 43));
                panel2.add(button4);

                // ---- button5 ----
                button5.setText("Nh\u1eadp Excel");
                button5.setPreferredSize(new Dimension(144, 43));
                panel2.add(button5);
            }
            panel1.add(panel2);

            // ======== panel3 ========
            {
                panel3.setLayout(new FlowLayout());

                // ======== paneltr ========
                {
                    paneltr.setLayout(new FlowLayout());
                    comboBox2.setPreferredSize(new Dimension(140, 40));
                    paneltr.add(comboBox2);
                }
                paneltr.setBorder(BorderFactory.createTitledBorder("Trạng thái tài khoản"));
                paneltr.setPreferredSize(new Dimension(180, 90));
                panel3.add(paneltr);

                // ======== panel5 ========
                {
                    panel5.setBorder(new TitledBorder("T\u00ecm ki\u1ebfm"));
                    panel5.setLayout(new FlowLayout());

                    // ---- comboBox1 ----
                    comboBox1.setPreferredSize(new Dimension(130, 40));
                    panel5.add(comboBox1);

                    // ---- textField1 ----
                    textField1.setPreferredSize(new Dimension(144, 55));
                    panel5.add(textField1);
                }
                panel3.add(panel5);

                // ---- button6 ----
                button6.setText("L\u00e0m m\u1edbi");
                button6.setPreferredSize(new Dimension(144, 45));
                panel3.add(button6);
            }
            panel1.add(panel3);

            // ======== panel4 ========
            {
                panel4.setLayout(new FlowLayout());
            }
            panel1.add(panel4);
        }
        add(panel1, BorderLayout.NORTH);

        // ======== scrollPane1 ========
        {

            // ---- table1 ----
            table1.setModel(new DefaultTableModel(
                    new Object[][] {
                            { null, null, null, null, null },
                            { null, null, null, null, null },
                    },
                    new String[] {
                            null, null, null, null, null
                    }));
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        // add icon
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
        ImageIcon refreshIcon = new ImageIcon(
                getClass().getResource("/org/example/GUI/resources/images/icons8_data_backup_30px.png"));

        button1.setIcon(iconThem);
        button2.setIcon(iconXoa);
        button3.setIcon(IconSua);
        button4.setIcon(exportIcon);
        button5.setIcon(importIcon);
        button6.setIcon(refreshIcon);

        // set font
        Font font = new Font("Segoe", 0, 16);
        Font fontHeader = new Font("Segoe", Font.BOLD, 16);

        // font for table1
        table1.setFont(font);
        table1.getTableHeader().setFont(fontHeader);

        // set size for table1
        table1.setRowHeight(30);
        TableColumnModel columnModel = table1.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(150);
        }

        // add item for comboBox
        String[] items = { "Tất cả", "Emai", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền" };
        for (String item : items) {
            comboBox1.addItem(item);
        }
        String[] itemss = { "Tất cả", "Đang hoạt động", "Ngừng hoạt động" };
        for (String item : itemss) {
            comboBox2.addItem(item);
        }
        // su kien khi nhan chon item
        // textField1.setBorder(BorderFactory.createTitledBorder("Tất cả"));
        // comboBox1.addItemListener(e -> {
        // if (e.getStateChange() == ItemEvent.SELECTED) {
        // String selectedItem = (String) comboBox1.getSelectedItem();
        // if (selectedItem.equals("Tất cả")) {
        // textField1.setBorder(BorderFactory.createTitledBorder("Tất cả"));
        // } else if (selectedItem.equals("Tên tài khoản")) {
        // textField1.setBorder(BorderFactory.createTitledBorder("Tên tài khoản"));
        // } else if (selectedItem.equals("Mật khẩu")) {
        // textField1.setBorder(BorderFactory.createTitledBorder("Mật khẩu"));
        // } else if (selectedItem.equals("Mã nhân viên")) {
        // textField1.setBorder(BorderFactory.createTitledBorder("Mã nhân viên"));
        // } else if (selectedItem.equals("Mã quyền")) {
        // textField1.setBorder(BorderFactory.createTitledBorder("Mã quyền"));
        // }
        // }
        // });

        // comboBox2.addItemListener(new ItemListener() {
        // @Override
        // public void itemStateChanged(ItemEvent itemEvent) {
        // if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
        // String selectedItem = (String) itemEvent.getItem();
        //
        // // qltk.readDB();
        // // ArrayList<Account> allAccounts = qltk.getDstk();
        // // setDataToTable(allAccounts);
        // if ("Tất cả".equals(selectedItem)) {
        // ArrayList<Account> accounts = qltk.getAllAccounts();
        // setDataToTable(accounts);
        // } else if ("Đang hoạt động".equals(selectedItem)) {
        // ArrayList<Account> accounts = qltk.getAccountsByEmployeeStatus();
        // setDataToTable(accounts);
        // } else if ("Ngừng hoạt động".equals(selectedItem)) {
        // ArrayList<Account> accounts = qltk.getInactiveAccountsByEmployeeStatus();
        // setDataToTable(accounts);
        // }
        // }
        // }
        // });

        // su kien khi nhan vao nut them
        // button1.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // System.out.println("Mở form thêm tài khoản...");
        // DialogAddAccount formAddUser = new DialogAddAccount("Thêm", "");
        // formAddUser.setVisible(true);
        // }
        //
        // });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogAddAccount dialog = new DialogAddAccount("Thêm", "");
    
                dialog.setVisible(true);
            }
        });

        // su kien khi nhan vao nut xoa
        // button2.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // int selectedRow = table1.getSelectedRow();
        // if (selectedRow == -1) {
        // JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản");
        // return;
        // }
        //
        // String maQuyen = table1.getValueAt(selectedRow, 5).toString();
        // if ("Q4".equals(maQuyen)) {
        // JOptionPane.showMessageDialog(null, "Không thể xóa tài khoản admin", "Thông
        // báo",
        // JOptionPane.WARNING_MESSAGE);
        // return;
        // }
        //
        // String userName = (String) table1.getValueAt(selectedRow, 2);
        //
        // // hoi trc khi xoa
        // int confirm = JOptionPane.showConfirmDialog(null,
        // "Bạn có chắc chắn muốn xóa tài khoản " + userName + "?", "Xác nhận xóa",
        // JOptionPane.YES_NO_OPTION);
        // if (confirm == JOptionPane.YES_OPTION) {
        // // xoa
        // if (qltk.delete(userName)) {
        // JOptionPane.showMessageDialog(null, "Xóa tài khoản " + userName + " thành
        // công");
        //
        // // Refresh the table data
        // refresh();
        // } else {
        // JOptionPane.showMessageDialog(null, "Xóa tài khoản không thành công");
        // }
        // }
        // }
        // });

        // su kien khi nhan vao nut sua
        // button3.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // int selectedRow = table1.getSelectedRow();
        // if (selectedRow == -1) {
        // JOptionPane.showMessageDialog(null, "Vui lòng chọn một tài khoản");
        // return;
        // }
        // String username = (String) table1.getValueAt(selectedRow, 2);
        //
        // // Tạo một đối tượng mới của AddButtonAccount với tham số đầu tiên là "Sửa"
        // và
        // // tham số thứ hai là tên tài khoản được chọn
        // AddButtonAccount account = new AddButtonAccount("Sửa", username);
        // account.setAccountAddedListener(new AccountAddedListener() {
        // @Override
        // public void onAccountAdded(Account newAccount) {
        // qltk.update(newAccount.getEmail(), newAccount.getUsername(),
        // newAccount.getPassword(),
        // newAccount.getMaNV(),
        // newAccount.getMaQuyen());
        // refresh();
        // }
        // });
        // // Hiển thị giao diện AddButtonAccount
        // account.setVisible(true);
        // }
        // });
        //
        // // xuat excel
        // button4.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // JFileChooser fileChooser = new JFileChooser();
        // fileChooser.setDialogTitle("Chọn nơi lưu file");
        //
        // int userSelection = fileChooser.showSaveDialog(null);
        //
        // if (userSelection == JFileChooser.APPROVE_OPTION) {
        // File fileToSave = fileChooser.getSelectedFile();
        //
        // Workbook workbook = new XSSFWorkbook();
        // Sheet sheet = workbook.createSheet("Tài khoản");
        //
        // // tao row header
        // Row headerRow = sheet.createRow(0);
        // headerRow.createCell(0).setCellValue("STT");
        // headerRow.createCell(1).setCellValue("Email");
        // headerRow.createCell(1).setCellValue("Tên tài khoản");
        // headerRow.createCell(2).setCellValue("Mật khẩu");
        // headerRow.createCell(3).setCellValue("Mã nhân viên");
        // headerRow.createCell(4).setCellValue("Mã quyền");
        //
        // // them du lieu vao sheet
        // for (int i = 0; i < table1.getRowCount(); i++) {
        // Row row = sheet.createRow(i + 1);
        // for (int j = 0; j < table1.getColumnCount(); j++) {
        // row.createCell(j).setCellValue(table1.getValueAt(i, j).toString());
        // }
        // }
        //
        // // ghi ra file
        // try (FileOutputStream outputStream = new FileOutputStream(fileToSave)) {
        // workbook.write(outputStream);
        // JOptionPane.showMessageDialog(null, "Xuất excel thành công");
        // } catch (IOException e) {
        // JOptionPane.showMessageDialog(null, "Xuất excel không thành công");
        // e.printStackTrace();
        // }
        // }
        // }
        // });

        // nhap excel
        // button5.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent actionEvent) {
        // JFileChooser fileChooser = new JFileChooser();
        // fileChooser.setDialogTitle("Chọn file Excel");
        //
        // int userSelection = fileChooser.showOpenDialog(null);
        //
        // if (userSelection == JFileChooser.APPROVE_OPTION) {
        // File fileToOpen = fileChooser.getSelectedFile();
        //
        // try (FileInputStream inputStream = new FileInputStream(fileToOpen)) {
        // Workbook workbook = new XSSFWorkbook(inputStream);
        //
        // Sheet sheet = workbook.getSheetAt(0);
        // Iterator<Row> rowIterator = sheet.iterator();
        //
        // // skip thee header row
        // if (rowIterator.hasNext()) {
        // rowIterator.next();
        // }
        //
        // while (rowIterator.hasNext()) {
        // Row row = rowIterator.next();
        // String email = row.getCell(1).getStringCellValue();
        // String tenTaiKhoan = row.getCell(1).getStringCellValue();
        // String matKhau = row.getCell(2).getStringCellValue();
        // String maNV = row.getCell(3).getStringCellValue();
        // String maQuyen = row.getCell(4).getStringCellValue();
        //
        // Account permission = new Account(email, tenTaiKhoan, matKhau, maNV, maQuyen);
        //
        // // add to the database
        // qltk.add(permission);
        // }
        //
        // //
        // refresh();
        //
        // JOptionPane.showMessageDialog(null, "Nhập file thành công");
        // } catch (IOException e) {
        // JOptionPane.showMessageDialog(null, "Nhập file không thành công");
        // e.printStackTrace();
        // }
        // }
        // }
        // });
        //
        // // search
        // textField1.getDocument().addDocumentListener(new DocumentListener() {
        // @Override
        // public void insertUpdate(DocumentEvent documentEvent) {
        // performSearch();
        // }
        //
        // @Override
        // public void removeUpdate(DocumentEvent documentEvent) {
        // performSearch();
        // }
        //
        // @Override
        // public void changedUpdate(DocumentEvent documentEvent) {
        // performSearch();
        // }
        //
        // public void performSearch() {
        // String value = textField1.getText();
        // String type = (String) comboBox1.getSelectedItem();
        // ArrayList<Account> result = qltk.search(value, type);
        // setDataToTable(result);
        // }
        // });
        // }
        //
        // // thong bao khi mot tai khoan moi duoc them
        // public interface AccountAddedListener {
        // void onAccountAdded(Account newAccount);
        // }
        //
        // // refresh data in table
        // public void refresh() {
        // // qltk.readDB();
        // // setDataToTable(qltk.getDstk());
        // ArrayList<Account> data = qltk.getAccountsByEmployeeStatus();
        // setDataToTable(data);
        // }

        // set data for table1
        // public void setDataToTable(ArrayList<Account> data) {
        // DefaultTableModel tableModel = new DefaultTableModel() {
        // @Override
        // public boolean isCellEditable(int row, int column) {
        // // Debug statement to check row index
        // System.out.println("Checking if row " + row + " is editable");
        // // Make the row with sequence number 1 (index 1) non-editable
        // return row != 1;
        // }
        // };
        //
        // tableModel.addColumn("STT");
        // tableModel.addColumn("Email");
        // tableModel.addColumn("Tên tài khoản");
        // tableModel.addColumn("Mật khẩu");
        // tableModel.addColumn("Mã nhân viên");
        // tableModel.addColumn("Mã quyền");
        //
        // int stt = 1;
        // for (Account account : data) {
        // tableModel.addRow(new Object[] {
        // stt++,
        // account.getEmail(),
        // account.getUsername(),
        // account.getPassword(),
        // account.getMaNV(),
        // account.getMaQuyen()
        // });
        // }
        // table1.setModel(tableModel);
        // }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Master
        }
    public interface AccountAddedListener {
        void onAccountAdded(UsersDTO newAccount);
    }
    private JPanel panel1;
    private JPanel panel2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JPanel panel3;
    private JPanel panel5;
    private JPanel paneltr;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JButton button6;
    private JPanel panel4;
    private JScrollPane scrollPane1;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

// class Main {
// public static void main(String[] args) throws
// UnsupportedLookAndFeelException, ClassNotFoundException,
// InstantiationException, IllegalAccessException {
// for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
// .getInstalledLookAndFeels()) {
// if ("Nimbus".equals(info.getName())) {
// javax.swing.UIManager.setLookAndFeel(info.getClassName());
// break;
// }
// }
// FormAccount formAccount = new FormAccount();
// JFrame frame = new JFrame("Form Account");
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// frame.getContentPane().add(formAccount);
// frame.pack();
// frame.setLocationRelativeTo(null);
// frame.setVisible(true);
// }
