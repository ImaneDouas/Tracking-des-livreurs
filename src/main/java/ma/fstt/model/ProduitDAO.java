package ma.fstt.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO extends BaseDAO<Produit> {

    public ProduitDAO() throws SQLException {
    }

    @Override
    public void save(Produit produit) throws SQLException {
        String query = "insert into produit (nomPrd, prix_Unitaire, description) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, produit.getNomPrd());
        statement.setDouble(2, produit.getPrix_Unitaire());
        statement.setString(3, produit.getDescription());
        statement.executeUpdate();
    }

    @Override
    public void update(Produit produit) throws SQLException {
        String query = "update produit set nomPrd = ?, prix_Unitaire = ?, description = ? where id_prd = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, produit.getNomPrd());
        statement.setDouble(2, produit.getPrix_Unitaire());
        statement.setString(3, produit.getDescription());
        statement.setLong(4, produit.getId_prd());
        statement.executeUpdate();
    }

    @Override
    public void delete(Produit produit) throws SQLException {
        String query = "delete from produit where id_prd = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, produit.getId_prd());
        statement.executeUpdate();
    }

    @Override
    public List<Produit> getAll() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "select * from produit";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Produit produit = new Produit();
            produit.setId_prd(resultSet.getLong("id_prd"));
            produit.setNomPrd(resultSet.getString("nomPrd"));
            produit.setPrix_Unitaire(resultSet.getDouble("prix_Unitaire"));
            produit.setDescription(resultSet.getString("description"));
            produits.add(produit);
        }
        return produits;
    }

    @Override
    public Produit getOne(Long id) throws SQLException {
        String query = "SELECT * FROM produit WHERE id_prd = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Produit produit = new Produit();
            produit.setId_prd(resultSet.getLong("id_prd"));
            produit.setNomPrd(resultSet.getString("nomPrd"));
            produit.setPrix_Unitaire(resultSet.getDouble("prix_Unitaire"));
            produit.setDescription(resultSet.getString("description"));
            return produit;
        }
        return null;
    }
}
