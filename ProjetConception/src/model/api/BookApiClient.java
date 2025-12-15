package model.api;

import model.Book;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class BookApiClient {

    private static final String BOOKS_URL = "https://mastersautocleaners.com/library/selectallbooks.php";

    public HashMap<String, Book> fetchBooks() {

        HashMap<String, Book> books = new HashMap<>();

        try {
            URL url = new URL(BOOKS_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String json = br.readLine();
            System.out.println("=== JSON RECUS ===");
            System.out.println(json);
            System.out.println("==================");


            JSONObject root = new JSONObject(json);
            JSONArray data = root.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {

                JSONObject o = data.getJSONObject(i);

                String rfid = String.valueOf(o.get("Rfid")).trim();

                Book b = new Book(
                	    o.getInt("book_Id"),
                	    rfid,
                	    o.getString("Titre"),
                	    o.getString("Auteur"),
                	    o.getString("Edition"),
                	    o.getString("Date_Parution"),
                	    o.getInt("Nombre_pages"),
                	    o.getString("Status")
                	);
                books.put(b.getRfid(), b);
            }

            return books;

        } catch (Exception e) {
            System.out.println("Erreur fetchBooks: " + e.getMessage());
            return books;
        }
    }
}
