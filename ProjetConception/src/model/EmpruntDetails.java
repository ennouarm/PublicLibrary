package model;

public class EmpruntDetails {

    private String rfid;
    private String titre;
    private String auteur;
    private String dateEmprunt;
    private String dateRetour;
    private Utilisateur utilisateur;

    public EmpruntDetails(String rfid, String titre, String auteur, 
                          String dateEmprunt, String dateRetour, 
                          Utilisateur utilisateur) {

        this.rfid = rfid;
        this.titre = titre;
        this.auteur = auteur;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.utilisateur = utilisateur;
    }

    public String getRfid() { return rfid; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getDateEmprunt() { return dateEmprunt; }
    public String getDateRetour() { return dateRetour; }
    public Utilisateur getUtilisateur() { return utilisateur; }
}
