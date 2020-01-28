package poo_graphic;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poo_graphic.Models.*;

import java.io.IOException;

public class ListBiensController extends DetailsController{
    protected Agence immoEsi;

    @FXML
    protected ListView<Bien> list_biens;
    @FXML
    protected TableColumn<Bien, String> bienColumn;
    @FXML
    protected Button log;
//    @FXML
//    private void initialize() {
//        list_biens.setCellFactory((ListView<Bien> l) -> new BienTile());
//    }

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;

        list_biens.setItems(immoEsi.getBiens());
        list_biens.setCellFactory((ListView<Bien> l) -> new BienTile());

        list_biens.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Bien>() {
            @Override
            public void onChanged(Change<? extends Bien> change) {
                afficherBienDetail(list_biens.getSelectionModel().getSelectedItem());
            }
        });
    }

}
