package ma.fstt.model;

import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) {

// trait bloc try catch
        try {


            LivreurDAO livreurDAO = new LivreurDAO();
          //  Livreur liv = new Livreur(0l , "liv3" , "200000000");

            //livreurDAO.save(liv);

            //Livreur liv2 = new Livreur(0l , "liv2" , "100000000");


           // livreurDAO.save(liv2);


          List<Livreur> livlist =  livreurDAO.getAll();

            for (Livreur liv :livlist) {

                System.out.println(liv.toString());

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


                try {
                    // Test de la classe CommandeDAO
                    CommandeDAO commandeDAO = new CommandeDAO();
                    List<Commande> commandes = commandeDAO.getAll();
                    System.out.println("Liste des commandes :");
                    for (Commande commande : commandes) {
                        System.out.println(commande);
                    }
//
                    Commande commande1 = new Commande();
                    commandeDAO.save(commande1);
                    System.out.println("Nouvelle commande ajoutée : " + commande1);

                    commande1.setEtat("livrée");
                    commandeDAO.update(commande1);
                    System.out.println("Commande modifiée : " + commande1);

                    commandeDAO.delete(commande1);
                    System.out.println("Commande supprimée : " + commande1);

                    Commande commande2 = commandeDAO.getOne(1L);
                    System.out.println("Commande avec l'ID 1 : " + commande2);

                    // Test de la classe ProduitDAO
                    ProduitDAO produitDAO = new ProduitDAO();
                    List<Produit> produits = produitDAO.getAll();
                    System.out.println("Liste des produits :");
                    for (Produit produit : produits) {
                        System.out.println(produit);
                    }

                    Produit produit1 = new Produit(10L, "produit1", 10.0D, "description1");
                    produitDAO.save(produit1);
                    System.out.println("Nouveau produit ajouté : " + produit1);

                    produit1.setPrix_Unitaire(15.0D);
                    produitDAO.update(produit1);
                    System.out.println("Produit modifié : " + produit1);

                    produitDAO.delete(produit1);
                    System.out.println("Produit supprimé : " + produit1);

                    Produit produit2 = produitDAO.getOne(1L);
                    System.out.println("Produit avec l'ID 1 : " + produit2);
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());

                }
    }
}



