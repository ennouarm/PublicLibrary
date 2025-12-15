package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RecuPanel extends JPanel {

    private JButton btnOui;
    private JButton btnNon;

    public RecuPanel() {

        setLayout(new FlowLayout());
        setBackground(new Color(250, 250, 250));

        btnOui = new JButton("Oui");
        btnOui.setBackground(new Color(39, 174, 96));
        btnOui.setForeground(Color.WHITE);

        btnNon = new JButton("Non");
        btnNon.setBackground(new Color(192, 57, 43));
        btnNon.setForeground(Color.WHITE);
        
        add(new JLabel("As-tu terminé les emprunts ?"));
        add(btnOui);
        add(btnNon);
    }

    public void addYesListener(ActionListener l) {
        btnOui.addActionListener(l);
    }

    public void addNoListener(ActionListener l) {
        btnNon.addActionListener(l);
    }
}
