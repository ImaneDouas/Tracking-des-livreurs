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
import ma.fstt.model.Produit;
import ma.fstt.model.ProduitDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {
        @FXML
        private TextField txt_prd;

        @FXML
        private TextField txt_price;

        @FXML
        private TextField txt_desc;

        @FXML
        private TableView<Produit> tableView;

        @FXML
        private TableColumn<Produit, Long> col_id;

        @FXML
        private TableColumn<Produit, String> col_nom;

        @FXML
        private TableColumn<Produit, Double> col_prix;

        @FXML
        private TableColumn<Produit, String> col_desc;
        @FXML
        private Button exit;

        @FXML
        protected void handleAjouter() {
                // Accès à la base de données
                try {
                        ProduitDAO produitDAO = new ProduitDAO();
                        System.out.println("txt_desc: " + txt_desc);
                        String priceText = txt_price.getText();
                        if (priceText.isEmpty()) {
                                // handle empty TextField
                                return;
                        }
                        Produit produit = new Produit(txt_prd.getText(), Double.parseDouble(priceText), txt_desc.getText());
                        produitDAO.save(produit);
                        updateTableView();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }


        @FXML
        protected void handleModifier() {
                // Accès à la base de données
                try {
                        ProduitDAO produitDAO = new ProduitDAO();
                        Produit produit = tableView.getSelectionModel().getSelectedItem();
                        if (produit != null) { // vérifier que l'objet produit n'est pas nul
                                produit.setNomPrd(txt_prd.getText());
                                produit.setPrix_Unitaire(Double.parseDouble(txt_price.getText()));
                                produit.setDescription(txt_desc.getText());
                                produitDAO.update(produit);
                                updateTableView();
                        }
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }



        @FXML
        protected void handleSupprimer() {
                // Accès à la base de données
                try {
                        ProduitDAO produitDAO = new ProduitDAO();
                        Produit produit = tableView.getSelectionModel().getSelectedItem();
                        produitDAO.delete(produit);
                        updateTableView();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        public void updateTableView() {
                col_id.setCellValueFactory(new PropertyValueFactory<>("id_prd"));
                col_nom.setCellValueFactory(new PropertyValueFactory<>("nomPrd"));
                col_prix.setCellValueFactory(new PropertyValueFactory<>("prix_Unitaire"));
                col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
                tableView.setItems(getDataProduits());
        }

        public static ObservableList<Produit> getDataProduits() {
                ProduitDAO produitDAO;
                ObservableList<Produit> listfx = FXCollections.observableArrayList();
                try {
                        produitDAO = new ProduitDAO();
                        listfx.addAll(produitDAO.getAll());
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
                return listfx;
        }

        @FXML
        public void exit() throws IOException {
                Parent menuViewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
                Scene menuViewScene = new Scene(menuViewParent);
                Stage window = (Stage) exit.getScene().getWindow();
                window.setScene(menuViewScene);
                window.show();
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                updateTableView();
                tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                        if (newSelection != null) {
                                txt_prd.setText(newSelection.getNomPrd());
                                txt_price.setText(String.valueOf(newSelection.getPrix_Unitaire()));
                                txt_desc.setText(newSelection.getDescription());
                        }
                });
        }
}