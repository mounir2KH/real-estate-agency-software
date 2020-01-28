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
import javafx.stage.Stage;
import poo_graphic.Models.*;

import java.beans.EventHandler;
import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poo_graphic.Models.*;

import java.io.IOException;

public class AdminPageController {

    private Agence immoEsi = new Agence();
    private boolean[] selectedView = {true, false, false, false};

    @FXML
    private AnchorPane view;

    @FXML
    private void logOut(MouseEvent event) {
        Stage stage = (Stage) view.getScene().getWindow();
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
    private void afficherBiens(MouseEvent event) {
        if (!selectedView[0]) {
            System.out.println("bien");
            resetView();
            afficherBiensUtil();
            for (int i = 0; i < 4; i++) {
                selectedView[i] = false;
            }
            selectedView[0] = true;
        }
    }

    @FXML
    private void afficherArchives(MouseEvent event) {
        if (!selectedView[1]) {
            resetView();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("Archives.fxml"));
                AnchorPane rootLayout = (AnchorPane) loader.load();
                ArchivesController controller = loader.getController();
                controller.setImmoEsi(immoEsi);
                view.getChildren().addAll(rootLayout);
            } catch (Exception err) {

            }
            for (int i = 0; i < 4; i++) {
                selectedView[i] = false;
            }
            selectedView[1] = true;
        }
    }

    @FXML
    private void afficherPropritaires(MouseEvent event) {
        if (!selectedView[2]) {
            resetView();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("ProprietairesAdmin.fxml"));
                AnchorPane rootLayout = (AnchorPane) loader.load();
                ProprietairesController controller = loader.getController();
                controller.setImmoEsi(immoEsi);
                view.getChildren().addAll(rootLayout);
            } catch (Exception err) {

            }
            for (int i = 0; i < 4; i++) {
                selectedView[i] = false;
            }
            selectedView[2] = true;
        }
    }

    @FXML
    private void afficherMessages(MouseEvent event) {
        if (!selectedView[3]) {
            resetView();
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("Messages.fxml"));
                AnchorPane rootLayout = (AnchorPane) loader.load();
                MessagesController controller = loader.getController();
                controller.setImmoEsi(immoEsi);
                view.getChildren().addAll(rootLayout);
            } catch (Exception err) {

            }
            for (int i = 0; i < 4; i++) {
                selectedView[i] = false;
            }
            selectedView[3] = true;
        }
    }

    private void afficherBiensUtil() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("BiensAdmin.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            BiensController controller = loader.getController();
            controller.setImmoEsi(immoEsi);
            view.getChildren().addAll(rootLayout);
        } catch (Exception err) {

        }
    }

    private void resetView() {
        if (view.getChildren().size() > 0)
            view.getChildren().remove(0);
    }

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;
        afficherBiensUtil();
    }
}
