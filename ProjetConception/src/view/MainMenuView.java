package view;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {

    private JButton scanBtn;
    private JButton mesEmpruntsBtn;
    private JButton logoutBtn;

    public MainMenuView() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        scanBtn = new JButton("Scanner RFID");
        mesEmpruntsBtn = new JButton("Mes emprunts");
        logoutBtn = new JButton("Déconnexion");

        styleButton(scanBtn);
        styleButton(mesEmpruntsBtn);

        logoutBtn.setBackground(new Color(192, 57, 43));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);

        scanBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        mesEmpruntsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scanBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(mesEmpruntsBtn);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(logoutBtn);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(new Color(236, 240, 241));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(150, 35));
    }

    // LISTENERS

    public void addScanListener(java.awt.event.ActionListener a) {
        scanBtn.addActionListener(a);
    }

    public void addMesEmpruntsListener(java.awt.event.ActionListener a) {
        mesEmpruntsBtn.addActionListener(a);
    }

    public void addLogoutListener(java.awt.event.ActionListener a) {
        logoutBtn.addActionListener(a);
    }
}
