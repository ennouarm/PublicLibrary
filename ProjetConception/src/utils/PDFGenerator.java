package utils;

import model.EmpruntDetails;
import model.Utilisateur;

import javax.swing.*;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    public static void generate(Utilisateur user, List<EmpruntDetails> emprunts) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Enregistrer votre reçu PDF");
            chooser.setSelectedFile(new java.io.File("Recu_" + user.getLogin() + ".pdf"));

            int result = chooser.showSaveDialog(null);
            if (result != JFileChooser.APPROVE_OPTION) return;

            java.io.File file = chooser.getSelectedFile();

            // --- Création d’un vrai PDF ---
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // --- On reprend TON contenu EXACT ---
            StringBuilder content = new StringBuilder();

            content.append("----- REÇU D'EMPRUNTS -----\n\n");
            content.append("Utilisateur : ").append(user.getNom()).append("\n");
            content.append("Login       : ").append(user.getLogin()).append("\n\n");
            content.append("Nombre total d'emprunts : ").append(emprunts.size()).append("\n\n");

            for (EmpruntDetails e : emprunts) {
                content.append("=== LIVRE ===\n");
                content.append("RFID    : ").append(e.getRfid()).append("\n");
                content.append("Titre   : ").append(e.getTitre()).append("\n");
                content.append("Auteur  : ").append(e.getAuteur()).append("\n");
                content.append("Emprunt : ").append(e.getDateEmprunt()).append("\n");
                content.append("Retour  : ").append(e.getDateRetour()).append("\n");
                content.append("---------------------------------\n\n");
            }

            content.append("Merci d'utiliser notre service !\n");

            // → On insère TON texte dans le PDF
            document.add(new Paragraph(content.toString(),
                    new Font(Font.FontFamily.COURIER, 12)));

            document.close();

            JOptionPane.showMessageDialog(
                    null,
                    "Reçu PDF généré :\n" + file.getAbsolutePath(),
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur PDF : " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
