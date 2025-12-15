package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {

    private JTextField loginField;
    private JPasswordField nipField;
    private JButton loginBtn;

    public LoginView() {

        setLayout(new GridLayout(3, 1, 10, 10));

        loginField = new JTextField();
        nipField = new JPasswordField();
        loginBtn = new JButton("Se connecter");

        add(loginField);
        add(nipField);
        add(loginBtn);
    }

    public String getLogin() { return loginField.getText().trim(); }

    public String getNip() { return new String(nipField.getPassword()); }

    public void addLoginListener(java.awt.event.ActionListener l) { loginBtn.addActionListener(l); }

    public void clearFields() {
        loginField.setText("");
        nipField.setText("");
    }
}
