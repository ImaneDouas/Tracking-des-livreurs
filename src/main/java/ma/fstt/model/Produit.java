package ma.fstt.model;

public class Produit {
    private Long id_prd ;
    private String nomPrd ;
    private Double prix_Unitaire ;
    private String description ;

    //Constructors

    public Produit(){

    }
    public Produit( String nomPrd,Double prix_Unitaire, String description ){

        this.nomPrd = nomPrd ;
        this.prix_Unitaire = prix_Unitaire ;
        this.description = description ;
    }
    public Produit(Long id_prd, String nomPrd,Double prix_Unitaire, String description ){
        this.id_prd = id_prd ;
        this.nomPrd = nomPrd ;
        this.prix_Unitaire = prix_Unitaire ;
        this.description = description ;
    }


    //Getters & Setters


    public Long getId_prd() {
        return id_prd;
    }

    public void setId_prd(Long id_prd) {
        this.id_prd = id_prd;
    }

    public String getNomPrd() {
        return nomPrd;
    }

    public void setNomPrd(String nomPrd) {
        this.nomPrd = nomPrd;
    }

    public Double getPrix_Unitaire() {
        return prix_Unitaire;
    }

    public void setPrix_Unitaire(Double prix_Unitaire) {
        this.prix_Unitaire = prix_Unitaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //toString() method


    @Override
    public String toString() {
        return "Produit{" +
                "id_prd=" + id_prd +
                ", nomPrd='" + nomPrd + '\'' +
                ", prix_Unitaire=" + prix_Unitaire +
                ", description='" + description + '\'' +
                '}';
    }

    public void save(Produit prd) {
    }
}
