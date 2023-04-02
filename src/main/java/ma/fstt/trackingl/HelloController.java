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
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private TextField nom ;


    @FXML
    private TextField tele ;


    @FXML
    private TableView<Livreur> mytable ;


    @FXML
    private TableColumn<Livreur ,Long> col_id ;

    @FXML
    private TableColumn <Livreur ,String> col_nom ;

    @FXML
    private TableColumn <Livreur ,String> col_tele ;
    @FXML
    private Button exitL;

    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            Livreur liv = new Livreur(0L, nom.getText() , tele.getText());

            livreurDAO.save(liv);


            UpdateTable();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        mytable.setItems(getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs(){

        LivreurDAO livreurDAO;
        ObservableList<Livreur> listfx = FXCollections.observableArrayList();
        try {
            livreurDAO = new LivreurDAO();
            listfx.addAll(livreurDAO.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx;

    }
    @FXML
    protected void onUpdateButtonClick() {
        // Accès à la base de données
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur livreur = mytable.getSelectionModel().getSelectedItem();
            // Vérifiez si livreur est null ou non
            if(livreur != null) {
                livreur.setNom(nom.getText());
                livreur.setTelephone(tele.getText());
                livreurDAO.update(livreur);
                UpdateTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        // Accès à la base de données
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur livreur = mytable.getSelectionModel().getSelectedItem();
            livreurDAO.delete(livreur);
            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void exitL() throws IOException {
        Parent menuViewParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene menuViewScene = new Scene(menuViewParent);
        Stage window = (Stage) exitL.getScene().getWindow();
        window.setScene(menuViewScene);
        window.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        mytable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nom.setText(newSelection.getNom());
                tele.setText(newSelection.getTelephone());
            }
        });

    }
}