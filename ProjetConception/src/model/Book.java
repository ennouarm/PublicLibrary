package model;

public class Book {

    private int id;
    private String rfid;
    private String titre;
    private String auteur;
    private String edition;
    private String dateParution;
    private int nombrePages;
    private String status;

    public Book(int id, String rfid, String titre, String auteur, String edition,
                String dateParution, int nombrePages, String status) {

        this.id = id;
        this.rfid = rfid;
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.dateParution = dateParution;
        this.nombrePages = nombrePages;
        this.status = status;
    }

    public int getId() { return id; }
    public String getRfid() { return rfid; }
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public String getEdition() { return edition; }
    public String getDateParution() { return dateParution; }
    public int getNombrePages() { return nombrePages; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
