package org.example.GUI.Application.other;

import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import net.miginfocom.swing.MigLayout;

public class PanelLogin extends JPanel {

   public PanelLogin() {
       setLayout(new MigLayout("fillx,wrap,insets 30 40 50 40, width 320", "[fill]", "[]20[][]15[][]30[]"));
       putClientProperty(FlatClientProperties.STYLE, ""
               + "background:$Login.background;"
               + "arc:20;");
   }

}

