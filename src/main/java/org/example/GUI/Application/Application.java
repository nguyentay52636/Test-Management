package org.example.GUI.Application;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.example.GUI.Application.other.MainForm;
import org.example.GUI.Components.FormAuth.LoginForm;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

/**
 *
 * @author Raven
 */
public class Application extends javax.swing.JFrame {

    private static Application app;
    private final MainForm mainForm;
    public static LoginForm loginForm;

    public Application() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();

        setContentPane(mainForm);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);

    }

    public static Application getInstance() {
        return app;
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() {
        if (app == null) {
            System.err.println("Application chưa được khởi tạo!");
            return;
        }

        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        if (app == null) {
            System.err.println("Application chưa được khởi tạo!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc chắn muốn đăng xuất không?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            FlatAnimatedLafChange.showSnapshot();
            loginForm = new LoginForm();
            loginForm.setVisible(true);
            app.dispose();
            // app.setContentPane(loginForm);
            // loginForm.applyComponentOrientation(app.getComponentOrientation());
            // SwingUtilities.updateComponentTreeUI(app);
            // FlatAnimatedLafChange.hideSnapshotWithAnimation();
        }
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 719, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 521, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("org.example.GUI.menu.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            // app.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
