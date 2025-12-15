package controller;

import model.Book;
import model.EmpruntDetails;
import model.Utilisateur;
import service.Bibliotheque;
import utils.PDFGenerator;
import view.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class LibraryController {

    private Bibliotheque biblio;
    private LibraryFrame frame;

    private LoginView loginView;
    private MainMenuView menuView;
    private ScannerPanel scanView;
    private ListeEmpruntView listeView;

    private Utilisateur utilisateurActuel;

    public LibraryController(Bibliotheque b, LibraryFrame f) {
        this.biblio = b;
        this.frame = f;

        loginView = new LoginView();
        menuView = new MainMenuView();
        scanView = new ScannerPanel();
        listeView = new ListeEmpruntView();

        frame.addPanel(loginView, LibraryFrame.LOGIN);
        frame.addPanel(menuView, LibraryFrame.MENU);
        frame.addPanel(scanView, LibraryFrame.SCAN);
        frame.addPanel(listeView, LibraryFrame.LISTE);

        initListeners();

        frame.showPanel(LibraryFrame.LOGIN);
    }

    private void initListeners() {

        // LOGIN
        loginView.addLoginListener(e -> {
            String login = loginView.getLogin();
            String nip = loginView.getNip();

            Utilisateur u = biblio.login(login, nip);
            if (u == null) {
                JOptionPane.showMessageDialog(null, "Login ou NIP incorrect !");
                return;
            }

            utilisateurActuel = u;
            scanView.clearList();
            biblio.cancelAllTemps(u); // Au cas où
            frame.showPanel(LibraryFrame.SCAN);
        });

        // MENU
        menuView.addScanListener(e -> frame.showPanel(LibraryFrame.SCAN));

        menuView.addMesEmpruntsListener(e -> {
            listeView.afficher(biblio.getEmpruntsValides(utilisateurActuel));
            frame.showPanel(LibraryFrame.LISTE);
        });

        menuView.addLogoutListener(e -> {
            if (utilisateurActuel != null)
                biblio.cancelAllTemps(utilisateurActuel);

            utilisateurActuel = null;
            loginView.clearFields();
            frame.showPanel(LibraryFrame.LOGIN);
        });

        // SCANNER
        scanView.addScanListener(new ScannerListener());
        scanView.addRetirerListener(new RetirerListener());
        scanView.addTerminerListener(e -> terminerEmprunts());
        scanView.addRetourListener(e -> frame.showPanel(LibraryFrame.MENU));

        listeView.addBackListener(e -> frame.showPanel(LibraryFrame.MENU));
    }

    // ----------- SCAN RFID ---------------
    private class ScannerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String rfid = scanView.getRfid().trim();
            if (rfid.isEmpty()) return;

            Book b = biblio.getBook(rfid);

            if (b == null) {
                JOptionPane.showMessageDialog(null, "Livre introuvable !");
                return;
            }

            if (!b.getStatus().equalsIgnoreCase("disponible")) {
                JOptionPane.showMessageDialog(null, "Ce livre est déjà emprunté !");
                return;
            }

            List<EmpruntDetails> temp = biblio.getEmpruntsTemp(utilisateurActuel);
            if (temp.size() >= 4) {
                JOptionPane.showMessageDialog(null, "Limite atteinte (4 emprunts max) !");
                return;
            }

            EmpruntDetails emp = biblio.enregistrerEmpruntTemp(b, utilisateurActuel);

            String display = emp.getTitre() + " — " + emp.getAuteur()
                    + " | Emprunt : " + emp.getDateEmprunt()
                    + " | Retour : " + emp.getDateRetour();

            scanView.addEmpruntString(display);
            scanView.clearRfid(); // AUTO CLEAR
        }
    }

    // ----------- RETIRER ---------------
    private class RetirerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int index = scanView.getSelectedIndex();
            if (index < 0) return;

            List<EmpruntDetails> list = biblio.getEmpruntsTemp(utilisateurActuel);
            EmpruntDetails emp = list.get(index);

            biblio.removeEmpruntTemp(utilisateurActuel, emp);
            scanView.removeEmpruntAt(index);
        }
    }

    // ----------- TERMINER ---------------
    private void terminerEmprunts() {

        List<EmpruntDetails> temp = biblio.getEmpruntsTemp(utilisateurActuel);
        if (temp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun emprunt !");
            return;
        }

        int choix = JOptionPane.showConfirmDialog(null,
                "Voulez-vous un reçu PDF ?",
                "Reçu",
                JOptionPane.YES_NO_OPTION);

        if (choix == JOptionPane.YES_OPTION) {
            PDFGenerator.generate(utilisateurActuel, temp);
        }

        biblio.validerEmprunts(utilisateurActuel);
        scanView.clearList();

        JOptionPane.showMessageDialog(null, "Emprunts confirmés !");
        frame.showPanel(LibraryFrame.MENU);
    }
}
