package service;

import model.Book;
import model.EmpruntDetails;
import model.Utilisateur;
import model.repo.BookRepository;
import model.repo.UserRepository;

import java.time.LocalDate;
import java.util.*;

public class Bibliotheque {

    private BookRepository bookRepo;
    private UserRepository userRepo;

    // Emprunts validés (historiques)
    private Map<String, List<EmpruntDetails>> empruntsValides = new HashMap<>();

    // Emprunts en cours de transaction (session)
    private Map<String, List<EmpruntDetails>> empruntsTemp = new HashMap<>();

    public Bibliotheque(BookRepository bookRepo, UserRepository userRepo) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    // ---------------- AUTH ----------------

    public Utilisateur login(String login, String nip) {
        return userRepo.authenticate(login, nip);
    }

    public boolean loginExiste(String login) {
        return userRepo.getUserByLogin(login) != null;
    }

    // ---------------- LIVRES ----------------

    public Book getBook(String rfid) {
        return bookRepo.getBookByRfid(rfid);
    }

    // =============== EMPRUNTS TEMPORAIRES ===============

    public EmpruntDetails enregistrerEmpruntTemp(Book book, Utilisateur utilisateur) {

        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetour = dateEmprunt.plusDays(14);

        EmpruntDetails emp = new EmpruntDetails(
                book.getRfid(),
                book.getTitre(),
                book.getAuteur(),
                dateEmprunt.toString(),
                dateRetour.toString(),
                utilisateur
        );

        empruntsTemp.computeIfAbsent(utilisateur.getLogin(), k -> new ArrayList<>());
        empruntsTemp.get(utilisateur.getLogin()).add(emp);

        // Bloquer temporairement le livre
        book.setStatus("temp");
        bookRepo.updateBook(book);

        return emp;
    }

    public void removeEmpruntTemp(Utilisateur utilisateur, EmpruntDetails emp) {
        List<EmpruntDetails> list = empruntsTemp.get(utilisateur.getLogin());
        if (list != null) list.remove(emp);

        // Rendre le livre disponible
        Book b = bookRepo.getBookByRfid(emp.getRfid());
        if (b != null) {
            b.setStatus("disponible");
            bookRepo.updateBook(b);
        }
    }

    public List<EmpruntDetails> getEmpruntsTemp(Utilisateur u) {
        return empruntsTemp.getOrDefault(u.getLogin(), new ArrayList<>());
    }

    // =============== VALIDER LES EMPRUNTS ===============

    public void validerEmprunts(Utilisateur utilisateur) {

        List<EmpruntDetails> temp = empruntsTemp.get(utilisateur.getLogin());
        if (temp == null || temp.isEmpty()) return;

        empruntsValides.computeIfAbsent(utilisateur.getLogin(), k -> new ArrayList<>());
        empruntsValides.get(utilisateur.getLogin()).addAll(temp);

        // Changer statut livres -> emprunté
        for (EmpruntDetails emp : temp) {
            Book b = bookRepo.getBookByRfid(emp.getRfid());
            if (b != null) {
                b.setStatus("emprunté");
                bookRepo.updateBook(b);
            }
        }

        // Nettoyer les emprunts temporaires
        temp.clear();
    }

    // =============== ANNULER TOUT ===============

    public void cancelAllTemps(Utilisateur utilisateur) {
        List<EmpruntDetails> temp = empruntsTemp.get(utilisateur.getLogin());
        if (temp == null) return;

        for (EmpruntDetails emp : temp) {
            Book b = bookRepo.getBookByRfid(emp.getRfid());
            if (b != null) {
                b.setStatus("disponible");
                bookRepo.updateBook(b);
            }
        }

        temp.clear();
    }

    // =============== HISTORIQUE ===============

    public List<EmpruntDetails> getEmpruntsValides(Utilisateur u) {
        return empruntsValides.getOrDefault(u.getLogin(), new ArrayList<>());
    }
}
