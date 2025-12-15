package model;

public class Utilisateur {

    private int id;
    private String login;
    private String nom;
    private String nip;

    public Utilisateur(int id, String login, String nom, String nip) {
        this.id = id;
        this.login = login;
        this.nom = nom;
        this.nip = nip;
    }

    public int getId() { return id; }
    public String getLogin() { return login; }
    public String getNom() { return nom; }
    public String getNip() { return nip; }

    @Override
    public String toString() {
        return login + " (" + nom + ")";
    }
}
