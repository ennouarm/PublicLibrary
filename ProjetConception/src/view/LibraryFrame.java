package view;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {

    public static final String LOGIN = "login";
    public static final String MENU = "menu";
    public static final String SCAN = "scan";
    public static final String LISTE = "liste";

    private CardLayout card = new CardLayout();
    private JPanel container = new JPanel(card);

    public LibraryFrame() {

        setTitle("Système de Bibliothèque");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(container);
    }

    public void addPanel(JPanel panel, String name) {
        container.add(panel, name);
    }

    public void showPanel(String name) {
        card.show(container, name);
    }
}
