package org.example.GUI.FormDialog.DiaLogForgetPass;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import org.example.BUS.UserBUS;
import org.example.DTO.UsersDTO;

public class DiaLogChangePass extends JPanel {
    private UsersDTO tk;

    private JPasswordField txMatKhauCu = new JPasswordField(15);
    private JPasswordField txMatKhauMoi = new JPasswordField(15);
    private JPasswordField txXacNhanMatKhau = new JPasswordField(15);
    
    private JButton btnDongY = new JButton("Đồng ý");
    private JButton btnHuy = new JButton("Hủy");

    public DiaLogChangePass(String matk) {
        this.setLayout(new BorderLayout(10, 10));
        tk = new UserBUS().getUser(matk);

        // Input Panel using BoxLayout
        JPanel plInput = new JPanel();
        plInput.setLayout(new BoxLayout(plInput, BoxLayout.Y_AXIS));
        plInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        plInput.setBackground(new Color(240, 248, 255));

        // TitledBorder styling and input field setup
        txMatKhauCu.setBorder(BorderFactory.createTitledBorder("🔑 Mật khẩu cũ: "));
        txMatKhauMoi.setBorder(BorderFactory.createTitledBorder("🔒 Mật khẩu mới: "));
        txXacNhanMatKhau.setBorder(BorderFactory.createTitledBorder("✅ Xác nhận mật khẩu: "));
        
        // Set component size
        txMatKhauCu.setMaximumSize(new Dimension(Integer.MAX_VALUE, txMatKhauCu.getPreferredSize().height));
        txMatKhauMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, txMatKhauMoi.getPreferredSize().height));
        txXacNhanMatKhau.setMaximumSize(new Dimension(Integer.MAX_VALUE, txXacNhanMatKhau.getPreferredSize().height));

        plInput.add(txMatKhauCu);
        plInput.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between fields
        plInput.add(txMatKhauMoi);
        plInput.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between fields
        plInput.add(txXacNhanMatKhau);

        this.add(plInput, BorderLayout.CENTER);

        // Button Panel
        JPanel plButton = new JPanel();
        plButton.setBackground(Color.WHITE);
        plButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center buttons with spacing
        btnDongY.setBackground(new Color(50, 205, 50));
        btnDongY.setForeground(Color.WHITE);
        btnDongY.setPreferredSize(new Dimension(120, 40));
        btnHuy.setBackground(new Color(220, 20, 60));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setPreferredSize(new Dimension(120, 40));

        plButton.add(btnDongY);
        plButton.add(btnHuy);

        // Load icons safely
        URL cancelIconUrl = getClass().getResource("/org/example/GUI/resources/images/icons8_cancel_30px_1.png");
        URL okIconUrl = getClass().getResource("/org/example/GUI/resources/images/icons8_add_30px.png");
        
        if (cancelIconUrl != null) {
            btnHuy.setIcon(new ImageIcon(cancelIconUrl));
        }
        if (okIconUrl != null) {
            btnDongY.setIcon(new ImageIcon(okIconUrl));
        }

        btnHuy.addActionListener(ae -> {

            txMatKhauCu.setText("");
            txMatKhauMoi.setText("");
            txXacNhanMatKhau.setText("");
            
           
        
        });
        
        btnDongY.addActionListener(ae -> {
            if (checkPass()) {
                if (new UserBUS().updateUser(tk)) {
                    JOptionPane.showMessageDialog(this, "🎉 Đổi mật khẩu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.getWindowAncestor(this).dispose();
                }
            }
        });

        this.add(plButton, BorderLayout.SOUTH);
    }

    private Boolean checkPass() {
        String mkcu = new String(txMatKhauCu.getPassword());
        String mkmoi = new String(txMatKhauMoi.getPassword());
        String xnmk = new String(txXacNhanMatKhau.getPassword());
        
        if (!mkcu.equals(tk.getUserPassword())) {
            JOptionPane.showMessageDialog(this, "❌ Mật khẩu cũ không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txMatKhauCu.requestFocus();
            return false;
        } else if (mkmoi.isEmpty() || xnmk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠️ Mật khẩu mới không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txMatKhauMoi.requestFocus();
            return false;
        } else if (!mkmoi.equals(xnmk)) {
            JOptionPane.showMessageDialog(this, "❌ Mật khẩu mới không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            txXacNhanMatKhau.requestFocus();
            return false;
        }
        return true;
    }
}
