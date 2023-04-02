package ma.fstt.trackingl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    private Button Liv;

    @FXML
    private Button cmd;

    @FXML
    private Button prd;

    @FXML
    public void handleLivButton() throws IOException {
        Parent livViewParent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene livViewScene = new Scene(livViewParent);
        Stage window = (Stage) Liv.getScene().getWindow();
        window.setScene(livViewScene);
        window.show();
    }

    @FXML
    public void handleCmdButton() throws IOException {
        Parent cmdViewParent = FXMLLoader.load(getClass().getResource("commande-view.fxml"));
        Scene cmdViewScene = new Scene(cmdViewParent);
        Stage window = (Stage) cmd.getScene().getWindow();
        window.setScene(cmdViewScene);
        window.show();
    }

    @FXML
   public void handlePrdButton() throws IOException {
        Parent prdViewParent = FXMLLoader.load(getClass().getResource("prd-view.fxml"));
        Scene prdViewScene = new Scene(prdViewParent);
        Stage window = (Stage) prd.getScene().getWindow();
        window.setScene(prdViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

