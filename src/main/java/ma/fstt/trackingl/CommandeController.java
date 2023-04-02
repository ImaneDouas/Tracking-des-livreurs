package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.fstt.model.Commande;
import ma.fstt.model.CommandeDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {

    @FXML
    private TextField txt_client;

    @FXML
    private TextField txt_dateF;

    @FXML
    private TextField txt_dated;

    @FXML
    private TextField txt_etat;

    @FXML
    private TextField txt_km;

    @FXML
    private TableView<Commande> maTable;

    @FXML
    private TableColumn<Commande, Long> id_col;

    @FXML
    private TableColumn<Commande, String> col_cl;

    @FXML
    private TableColumn<Commande, String> col_dd;

    @FXML
    private TableColumn<Commande, String> col_df;

    @FXML
    private TableColumn<Commande, Double> col_km;

    @FXML
    private TableColumn<Commande, String> col_e;

    @FXML
    private Button exitt;

    @FXML
    protected void addButton() {
        try {
            CommandeDAO commandeDAO = new CommandeDAO();

            Commande cmd = new Commande(0L, 0L, 0L, txt_dated.getText(), txt_dateF.getText(), Double.parseDouble(txt_km.getText()), txt_client.getText(), txt_etat.getText());

            commandeDAO.save(cmd);

            updateMaTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void upButton() {
        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            Commande commande = maTable.getSelectionModel().getSelectedItem();
            if (commande != null) {
                commande.setDate_debut(txt_dated.getText());
                commande.setDate_fin(txt_dateF.getText());
                commande.setKm(Double.parseDouble(txt_km.getText()));
                commande.setClient(txt_client.getText());
                commande.setEtat(txt_etat.getText());
                commandeDAO.update(commande);
                updateMaTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void deleteButton() {
        try {
            CommandeDAO commandeDAO = new CommandeDAO();
            Commande commande = maTable.getSelectionModel().getSelectedItem();
            commandeDAO.delete(commande);
            updateMaTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMaTable() {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id_cmd"));
        col_dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        col_df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        col_km.setCellValueFactory(new PropertyValueFactory<>("km"));
        col_cl.setCellValueFactory(new PropertyValueFactory<>("client"));
        col_e.setCellValueFactory(new PropertyValueFactory<>("etat"));
        maTable.setItems(getDataCommandes());
    }
    public static ObservableList<Commande> getDataCommandes() {
        CommandeDAO commandeDAO;
        ObservableList<Commande> listfx = FXCollections.observableArrayList();
        try {
            commandeDAO = new CommandeDAO();
            listfx.addAll(commandeDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx;
    }

    @FXML
    public void exitt() throws IOException {
        Parent menuViewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene menuViewScene = new Scene(menuViewParent);
        Stage window = (Stage) exitt.getScene().getWindow();
        window.setScene(menuViewScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateMaTable();
        maTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_dated.setText(newSelection.getDate_debut());
                txt_dateF.setText(newSelection.getDate_fin());
                txt_km.setText(String.valueOf(newSelection.getKm()));
                txt_client.setText(newSelection.getClient());
                txt_etat.setText(newSelection.getEtat());
            }
        });
    }
}