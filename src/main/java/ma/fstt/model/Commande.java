package ma.fstt.model;

public class Commande {
    private Long id_cmd ;
    private Long id_prd ; //clé étrangère vers la table des produits
    private Long id_livreur; //clé étrangère vers la table des livreurs
    private String date_debut ;
    private String date_fin ;
    private Double km ;
    private String client ;
    private String etat ;



    //constructeurs

    public Commande() {
    }


    public Commande(Long id_cmd, Long id_prd, Long id_livreur, String date_debut, String date_fin, Double km, String client, String etat) {
        this.id_cmd = id_cmd;
        this.id_prd = id_prd;
        this.id_livreur = id_livreur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.km = km;
        this.client = client;
        this.etat = etat;
    }

    public Commande(String date_debut, String date_fin, Double km, String client, String etat) {

    }



    //getters et setters

    public Long getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(Long id_cmd) {
        this.id_cmd = id_cmd;
    }

    public Long getId_prd() {
        return id_prd;
    }

    public void setId_prd(Long id_prd) {
        this.id_prd = id_prd;
    }

    public Long getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(Long id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public Double getKm() {

        return km;
    }


    public void setKm(Double km) {
        this.km = km;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    //méthode toString()

    @Override
    public String toString() {
        return "Commande{" +
                "id_cmd=" + id_cmd +
                ", id_prd=" + id_prd +
                ", id_livreur=" + id_livreur +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", km=" + km +
                ", client='" + client + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }



}