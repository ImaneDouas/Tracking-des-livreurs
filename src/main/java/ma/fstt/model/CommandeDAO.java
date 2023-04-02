package ma.fstt.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO extends BaseDAO<Commande> {

    private static final String INSERT_QUERY = "INSERT INTO commande (id_prd, id_livreur, date_debut, date_fin, km, client, etat) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE commande SET id_prd=?, id_livreur=?, date_debut=?, date_fin=?, km=?, client=?, etat=? WHERE id_cmd=?";
    private static final String DELETE_QUERY = "DELETE FROM commande WHERE id_cmd=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM commande";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM commande WHERE id_cmd=?";

    public CommandeDAO() throws SQLException {
        super();
    }

    @Override
    public void save(Commande commande) throws SQLException {
        Long idPrd = commande.getId_prd();
        Long idLiv = commande.getId_livreur();
        Double km = commande.getKm();
        String dateDebut = commande.getDate_debut();
        String dateFin = commande.getDate_fin();
        String client = commande.getClient();
        String etat = commande.getEtat();

        // Vérifier si la clé étrangère id_prd existe dans la table produit
        boolean idPrdExists = false;
        if (idPrd != null) {
            String checkQuery = "SELECT id_prd FROM produit WHERE id_prd = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setLong(1, idPrd);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        idPrdExists = true;
                    }
                }
            }
        }

        // Vérifier si la clé étrangère id_livreur existe dans la table livreur
        boolean idLivExists = false;
        if (idLiv != null) {
            String checkQuery = "SELECT id_livreur FROM livreur WHERE id_livreur = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setLong(1, idLiv);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        idLivExists = true;
                    }
                }
            }
        }

        // Insérer des valeurs par défaut pour id_prd et id_livreur s'ils n'existent pas
        if (!idPrdExists) {
            idPrd = 1L; // valeur par defaut
        }
        if (!idLivExists) {
            idLiv = 1L; //  valeur par défaut
        }

        // Insérer la commande dans la table commande
        String insertQuery = "INSERT INTO commande (id_prd, id_livreur, date_debut, date_fin, km, client, etat) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setLong(1, idPrd);
            insertStatement.setLong(2, idLiv);
            insertStatement.setString(3, dateDebut);
            insertStatement.setString(4, dateFin);
            if (km != null) {
                insertStatement.setDouble(5, km);
            } else {
                insertStatement.setNull(5, Types.DOUBLE);
            }
            insertStatement.setString(6, client);
            insertStatement.setString(7, etat);

            insertStatement.executeUpdate();
        }
    }




    @Override
    public void update(Commande commande) throws SQLException {
        preparedStatement = connection.prepareStatement(UPDATE_QUERY);

        preparedStatement.setLong(1, commande.getId_prd());
        preparedStatement.setLong(2, commande.getId_livreur());
        preparedStatement.setString(3, commande.getDate_debut());
        preparedStatement.setString(4, commande.getDate_fin());
        preparedStatement.setDouble(5, commande.getKm());
        preparedStatement.setString(6, commande.getClient());
        preparedStatement.setString(7, commande.getEtat());
        preparedStatement.setLong(8, commande.getId_cmd());

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Commande commande) throws SQLException {
        String request = "delete from commande where id_cmd = ?";
        this.preparedStatement = this.connection.prepareStatement(request);
        Long idCmd = commande.getId_cmd();
        if (idCmd != null) {
            preparedStatement.setLong(1, idCmd.longValue());
        }

         else{
            preparedStatement.setNull(1, Types.BIGINT);

         }
            this.preparedStatement.executeUpdate();

        }

    @Override
    public List<Commande> getAll() throws SQLException {
        List<Commande> commandes = new ArrayList<>();

        statement = connection.createStatement();
        resultSet = statement.executeQuery(SELECT_ALL_QUERY);

        while (resultSet.next()) {
            Commande commande = new Commande(
                    resultSet.getLong("id_cmd"),
                    resultSet.getLong("id_prd"),
                    resultSet.getLong("id_livreur"),
                    resultSet.getString("date_debut"),
                    resultSet.getString("date_fin"),
                    resultSet.getDouble("km"),
                    resultSet.getString("client"),
                    resultSet.getString("etat")
            );

            commandes.add(commande);
        }

        return commandes;
    }

    @Override
    public Commande getOne(Long id) throws SQLException {
        String query = "SELECT * FROM commande WHERE id_cmd = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Commande commande = new Commande();
            commande.setId_cmd(resultSet.getLong("id_cmd"));
            commande.setId_prd(resultSet.getLong("id_prd"));
            commande.setId_livreur(resultSet.getLong("id_livreur"));
            commande.setDate_debut(resultSet.getString("date_debut"));
            commande.setDate_fin(resultSet.getString("date_fin"));
            commande.setKm(resultSet.getDouble("km"));
            commande.setClient(resultSet.getString("client"));
            commande.setEtat(resultSet.getString("etat"));
            return commande;
        }
        return null;
    }
}