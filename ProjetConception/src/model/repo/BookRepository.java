package model.repo;

import model.Book;
import model.api.BookApiClient;

import java.util.HashMap;

public class BookRepository {

    private HashMap<String, Book> books;
    private final BookApiClient apiClient;

    public BookRepository(BookApiClient apiClient) {
        this.apiClient = apiClient;
        this.books = apiClient.fetchBooks();
        System.out.println("=== RFID CHARGÉS ===");
        for (String key : books.keySet()) {
            System.out.println("[" + key + "]");
        }
        System.out.println("====================");

    }

    /** Retourne un livre via son RFID */
    public Book getBookByRfid(String rfid) {
        return books.get(rfid);
    }

    /** Retourne tous les livres */
    public HashMap<String, Book> getAllBooks() {
        return books;
    }

    /** Mettre à jour localement (status, date, etc.) */
    public void updateBook(Book b) {
    	books.put(b.getRfid().trim(), b);
    }
    
}

