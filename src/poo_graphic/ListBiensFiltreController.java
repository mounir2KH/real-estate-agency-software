package poo_graphic;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import poo_graphic.Models.Transaction;
import poo_graphic.Models.Wilaya;

public class ListBiensFiltreController extends ListBiensController{
    /**
     * Filtrer
     **/
    @FXML
    private TextField prixMin, prixMax, superficieMin, nbrPiecesMin;
    @FXML
    private ChoiceBox<String> type_transaction, typeBien, wilaya;
    @FXML
    private Label filtreErreur;

    @FXML
    private  void filtre(MouseEvent event) {
        filtreUtil();
    }
    protected void filtreUtil() {
        try {
            filtreErreur.setText("");
            double superficieMinValue = 0, prixMinValue = 0, prixMaxValue = 0;
            int nbrpices = 0;
            String type = "";

            type = typeBien.getSelectionModel().getSelectedItem();
            Transaction transaction = null;
            if (!type_transaction.getSelectionModel().isEmpty()) {
                switch (type_transaction.getSelectionModel().getSelectedItem()) {
                    case "Vente":
                        transaction = Transaction.Vente;
                        break;
                    case "Location":
                        transaction = Transaction.Location;
                        break;
                    case "Echange":
                        transaction = Transaction.Echange;
                        break;
                }}

            if (!prixMin.getText().equals("")) {
                prixMinValue = Double.parseDouble(prixMin.getText());
            }
            if (!prixMax.getText().equals("")) {
                prixMaxValue = Double.parseDouble(prixMax.getText());
            }
            if (!superficieMin.getText().equals("")) {
                superficieMinValue = Double.parseDouble(superficieMin.getText());
            }
            if (!nbrPiecesMin.getText().equals("")) {
                nbrpices = Integer.parseInt(nbrPiecesMin.getText());
            }
            Wilaya wilayaValue = null;

            if (!wilaya.getSelectionModel().isEmpty()) {
                String wilayaString = wilaya.getSelectionModel().getSelectedItem();


                if (!wilayaString.equals(""))
                    wilayaValue = Wilaya.getWilaya(wilayaString);
            }
            String typeBienValue = "";

            if (!typeBien.getSelectionModel().isEmpty()) {
                typeBienValue = typeBien.getSelectionModel().getSelectedItem();
            }
            list_biens.setItems(immoEsi.filtrer(transaction, wilayaValue, prixMinValue, prixMaxValue, typeBienValue, superficieMinValue, nbrpices));
        } catch (Exception error) {
            filtreErreur.setText("filtres incorrects");
            list_biens.setItems(FXCollections.observableArrayList());
        }

    }

    @FXML
    private void reset(MouseEvent event) {
        filtreErreur.setText("");
        prixMin.setText("");
        prixMax.setText("");
        superficieMin.setText("");
        nbrPiecesMin.setText("");
        type_transaction.getSelectionModel().clearSelection();
        typeBien.getSelectionModel().clearSelection();
        wilaya.getSelectionModel().clearSelection();
    }

    @FXML
    private void initialize() {
        type_transaction.getItems().addAll("Vente", "Location", "Echange");
        typeBien.getItems().addAll("Maison", "Appartement", "Terrain");
        wilaya.getItems().addAll(Wilaya.getWilayas());
    }
}
