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

public class BiensController extends ListBiensFiltreController{

    @FXML
    private void ajouter(MouseEvent event) {
        System.out.println("ajouter");
    }
    @FXML
    private void modifier(MouseEvent event) {

    }
    @FXML
    private void archiver(MouseEvent event) {

    }
    @FXML
    private void supprimer(MouseEvent event) {

    }
}

