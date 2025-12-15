package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ScannerPanel extends JPanel {

    private JTextField txtRfid;
    private JButton btnScan;
    private JButton btnRetirer;
    private JButton btnRetour;
    private JButton btnTerminer;
    private DefaultListModel<String> model;
    private JList<String> list;

    public ScannerPanel() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        setBackground(Color.WHITE);

        // *********** TOP RFID ***********
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(Color.WHITE);
        top.add(new JLabel("RFID du livre : "));
        txtRfid = new JTextField(20);
        top.add(txtRfid);

        add(top, BorderLayout.NORTH);

        // *********** LISTE EMPRUNTS ***********
        model = new DefaultListModel<>();
        list = new JList<>(model);
        JScrollPane scroll = new JScrollPane(list);
        add(scroll, BorderLayout.CENTER);

        // *********** RETIRER ***********
        btnRetirer = new JButton("Retirer");
        btnRetirer.setBackground(new Color(231, 76, 60));
        btnRetirer.setForeground(Color.WHITE);

        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.add(btnRetirer);
        add(right, BorderLayout.EAST);

        // *********** BOTTOM ***********
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);

        btnScan = new JButton("Emprunter");
        btnRetour = new JButton("Retour");
        btnTerminer = new JButton("Terminer");

        bottom.add(btnScan);
        bottom.add(btnRetour);
        bottom.add(btnTerminer);

        add(bottom, BorderLayout.SOUTH);
    }

    public String getRfid() {
        return txtRfid.getText();
    }

    public void clearRfid() {
        txtRfid.setText("");
        txtRfid.requestFocus();
    }

    public void addEmpruntString(String s) {
        model.addElement(s);
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public void removeEmpruntAt(int index) {
        model.remove(index);
    }

    public void clearList() {
        model.clear();
    }

    // LISTENERS
    public void addScanListener(ActionListener l) { btnScan.addActionListener(l); }
    public void addRetirerListener(ActionListener l) { btnRetirer.addActionListener(l); }
    public void addRetourListener(ActionListener l) { btnRetour.addActionListener(l); }
    public void addTerminerListener(ActionListener l) { btnTerminer.addActionListener(l); }
}
