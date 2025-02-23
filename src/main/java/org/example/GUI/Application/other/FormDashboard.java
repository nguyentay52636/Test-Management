package org.example.GUI.Application.other;

import com.formdev.flatlaf.FlatClientProperties;

public class FormDashboard extends javax.swing.JPanel {

        public FormDashboard() {
                initComponents();
                lb.putClientProperty(FlatClientProperties.STYLE, "");
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lb = new javax.swing.JLabel();
                jButton1 = new javax.swing.JButton();

                lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lb.setText("Dashboard");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                794, Short.MAX_VALUE)
                                                                .addContainerGap())
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(325, 325, 325)
                                                                .addComponent(jButton1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lb)
                                                                .addGap(173, 173, 173)
                                                                .addComponent(jButton1)
                                                                .addContainerGap(237, Short.MAX_VALUE)));
        }

        private javax.swing.JButton jButton1;
        private javax.swing.JLabel lb;
        // End of variables declaration//GEN-END:variables
}
