import model.api.BookApiClient;
import model.api.UtilisateurApiClient;
import model.repo.BookRepository;
import model.repo.UserRepository;
import service.Bibliotheque;
import view.LibraryFrame;
import controller.LibraryController;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // 1) API Clients
            BookApiClient bookApi = new BookApiClient();
            UtilisateurApiClient userApi = new UtilisateurApiClient();

            // 2) Repositories (chargent les données dans les HashMaps)
            BookRepository bookRepo = new BookRepository(bookApi);
            UserRepository userRepo = new UserRepository(userApi);

            // 3) Service métier
            Bibliotheque bibliotheque = new Bibliotheque(bookRepo, userRepo);

            // 4) Interface principale
            LibraryFrame frame = new LibraryFrame();
            frame.setVisible(true);

            // 5) Controller (relie tout)
            new LibraryController(bibliotheque, frame);
        });
    }
}

