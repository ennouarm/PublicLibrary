package model.repo;

import model.Utilisateur;
import model.api.UtilisateurApiClient;

import java.util.HashMap;

public class UserRepository {

    private HashMap<String, Utilisateur> users;
    private final UtilisateurApiClient apiClient;

    public UserRepository(UtilisateurApiClient apiClient) {
        this.apiClient = apiClient;
        this.users = apiClient.fetchUsers();
    }

    /** Authentification via login + nip */
    public Utilisateur authenticate(String login, String nip) {
        Utilisateur u = users.get(login);

        if (u != null && u.getNip().equals(nip)) {
            return u;
        }
        return null;
    }

    /** Retourne tous les utilisateurs */
    public HashMap<String, Utilisateur> getAllUsers() {
        return users;
    }
    /** Vérifier si un utilisateur existe via son login */
    public Utilisateur getUserByLogin(String login) {
        return users.get(login);
    }

}
