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

public class UserPageController extends ListBiensFiltreController{

    @FXML
    private void logOut(MouseEvent mouseEvent) {
        Stage stage = (Stage) log.getScene().getWindow();
        Parent root;
        stage.setTitle("User");
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.fxml"));
            GridPane rootLayout = (GridPane) loader.load();
            Controller controller = loader.getController();
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
    private void envoyerMessage(MouseEvent event) {
        Stage stage = new Stage();
        try {
            Bien bien = list_biens.getSelectionModel().getSelectedItem();
            if (bien == null) {
                erreurSendMessage.setText("veuillez selectionner un bien");
                return;
            }
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sendMessage.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            SendMessageController controller = loader.getController();
            controller.setImmoEsi(immoEsi);
            controller.setBien(list_biens.getSelectionModel().getSelectedItem());

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

