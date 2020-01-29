package poo_graphic;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poo_graphic.Models.*;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Optional;

public class BiensController extends ListBiensFiltreController{

    @FXML
    private void ajouter(MouseEvent event) {
        Stage stage = new Stage();
        Parent root;
        stage.setTitle("Ajouter Bien");
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ajouterBien.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            ajouterBienController controller = loader.getController();
            controller.setImmoEsi(immoEsi);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void modifier(MouseEvent event) {
        Bien bien = list_biens.getSelectionModel().getSelectedItem();
        if (bien == null) {
            erreurSendMessage.setText("veuillez selectionner un bien");
            return;
        }
        Stage stage = new Stage();
        Parent root;
        stage.setTitle("Modifier Bien");
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("modifierBien.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            modifierBienController controller = loader.getController();
            controller.setAncienBien(bien);
            controller.setImmoEsi(immoEsi);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void archiver(MouseEvent event) {
        Bien bien = list_biens.getSelectionModel().getSelectedItem();
        if (bien == null) {
            erreurSendMessage.setText("veuillez selectionner un bien");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Etes vous sur d'archiver ce bien");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            immoEsi.archiver(bien);
            filtreUtil();
        }
    }
    @FXML
    private void supprimer(MouseEvent event) {
        Bien bien = list_biens.getSelectionModel().getSelectedItem();
        if (bien == null) {
            erreurSendMessage.setText("veuillez selectionner un bien");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Etes vous sur de supprimer ce bien");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            immoEsi.supprimerBien(bien);
            filtreUtil();
        }


    }
}

