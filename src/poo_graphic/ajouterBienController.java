package poo_graphic;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ajouterBienController {

    @FXML
    private ComboBox<?> echange_wilaya;

    @FXML
    private ToggleGroup Negociable;

    @FXML
    private ComboBox<?> wilayas;

    @FXML
    private RadioButton exchange;

    @FXML
    private TextField prixMin;

    @FXML
    private TextField superficieMin;

    @FXML
    private ComboBox<?> proprietaire;

    @FXML
    private Group meublé;
    @FXML
    private ToggleGroup type;

    @FXML
    private TextField Superficiehabitable;

    @FXML
    private ToggleGroup transaction;

    @FXML
    private ToggleGroup Meublé;

    @FXML
    private TextField prixMin1;

    @FXML
    private Group jardin;

    @FXML
    private ToggleGroup Jardin;

    @FXML
    private Group piscine;

    @FXML
    private ToggleGroup Piscine;
    @FXML
    private ToggleGroup Type;
    @FXML
    private ToggleGroup Transaction;

    @FXML
    private Group ascenceur;

    @FXML
    private Text Ascenceur;

    @FXML
    void Appartement(MouseEvent event) {
         meublé.setDisable(false);
        jardin.setDisable(true);
        piscine.setDisable(true);
        ascenceur.setDisable(false);
        Superficiehabitable.setDisable(true);
    }

    @FXML
    void Echange(MouseEvent event) {
        echange_wilaya.setDisable(false);
    }

    @FXML
    void Maison(MouseEvent event) {
        meublé.setDisable(false);
        jardin.setDisable(false);
        piscine.setDisable(false);
        ascenceur.setDisable(true);
        Superficiehabitable.setDisable(false);
    }

    @FXML
    void Terrain(MouseEvent event) {
        meublé.setDisable(true);
        jardin.setDisable(true);
        piscine.setDisable(true);
        ascenceur.setDisable(true);
        Superficiehabitable.setDisable(true);

    }

    @FXML
    void Location(MouseEvent event) {
        echange_wilaya.setDisable(true);
    }
    @FXML
    void Vente(MouseEvent event) {
        echange_wilaya.setDisable(true);

    }

}
