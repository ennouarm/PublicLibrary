package model.api;

import model.Utilisateur;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

public class UtilisateurApiClient {

    private static final String USERS_URL = "https://mastersautocleaners.com/library/selectallusers.php";

    public HashMap<String, Utilisateur> fetchUsers() {

        HashMap<String, Utilisateur> users = new HashMap<>();

        try {
            URL url = new URL(USERS_URL);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String json = br.readLine();

            JSONObject root = new JSONObject(json);
            JSONArray data = root.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject o = data.getJSONObject(i);

                Utilisateur u = new Utilisateur(
                        o.getInt("Identifiant"),
                        o.getString("userlogin"),
                        o.getString("Nom"),
                        String.valueOf(o.getInt("NIP"))
                );

                // clé = login
                users.put(u.getLogin(), u);
            }

            return users;

        } catch (Exception e) {
            System.out.println("Erreur fetchUsers: " + e.getMessage());
            return users;
        }
    }
}
