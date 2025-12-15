package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.EmpruntDetails;

public class ListeEmpruntView extends JPanel {

    private JTextArea area;
    private JButton backBtn;

    public ListeEmpruntView() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(area);
        add(scrollPane, BorderLayout.CENTER);

        backBtn = new JButton("Retour");
        backBtn.setPreferredSize(new Dimension(120, 35));
        backBtn.setBackground(new Color(200, 200, 255));

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.add(backBtn);

        add(bottom, BorderLayout.SOUTH);
    }

    public void afficher(List<EmpruntDetails> list) {

        area.setText("");

        if (list == null || list.isEmpty()) {
            area.setText("Aucun emprunt enregistré.");
            return;
        }

        StringBuilder text = new StringBuilder();

        for (EmpruntDetails e : list) {
            text.append("Titre : ").append(e.getTitre()).append("\n");
            text.append("Auteur : ").append(e.getAuteur()).append("\n");
            text.append("Emprunt : ").append(e.getDateEmprunt()).append("\n");
            text.append("Retour : ").append(e.getDateRetour()).append("\n");
            text.append("--------------------------------------\n\n");
        }

        area.setText(text.toString());
    }

    public void addBackListener(java.awt.event.ActionListener a) {
        backBtn.addActionListener(a);
    }
}
