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
    private Agence immoEsi;

    @FXML
    private ListView<Bien> list_biens;
    @FXML
    private TableView<Bien> bienTable;
    @FXML
    private TableColumn<Bien, String> bienColumn;
    @FXML
    private Button log;
    @FXML
    private Label addresse, description, prix, typeTransaction, negociable, superficie, date, proprietaire, nbrPieces, meuble, nbrEtage, duplexe;

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

    public void logOut(MouseEvent mouseEvent) {
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
    private void afficherBienDetail(Bien bien) {
        if (bien != null) {
            addresse.setText(bien.getAddresse().getValue());
            description.setText(bien.getDescription());
            prix.setText(Double.toString(bien.calculerPrix()));
            typeTransaction.setText(bien.getTypeTransaction().toString());
            negociable.setText((bien.isNegociable()) ? "Oui" : "Non");
            superficie.setText(Double.toString(bien.getSuperficie()));
            date.setText(bien.getDate().toString());
            proprietaire.setText(bien.getProprietaire().getNom());
            if (bien instanceof Habitable) {
                nbrPieces.setText(Integer.toString(((Habitable) bien).getNbrPieces()));
                meuble.setText((((Habitable) bien).isEstMeuble()) ? "Oui" : "Non");
                if (bien instanceof Appartement) {
                    nbrEtage.setText(Integer.toString(((Appartement) bien).getEtage()));
                    duplexe.setText((((Appartement) bien).isEstDuplex()) ? "Oui" : "Non");
                }
            }
        } else {

        }
    }

//    @FXML
//    private void envoyerMessage(MouseEvent event) {
//        Stage stage = new Stage();
//        try {
//            // Load root layout from fxml file.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("sendMessage.fxml"));
//            AnchorPane rootLayout = (AnchorPane) loader.load();
//            SendMessageController controller = loader.getController();
//            controller.setImmoEsi(immoEsi);
//            controller.setBien(list_biens.getSelectionModel().getSelectedItem());
//
//            // Show the scene containing the root layout.
//            Scene scene = new Scene(rootLayout);
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Filtrer
     **/
    @FXML
    private TextField prixMin, prixMax, superficieMin, nbrPiecesMin;
    @FXML
    private ChoiceBox<String> type_transaction, typeBien, wilaya;

    @FXML
    private void filtre(MouseEvent event) {
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
    }

    @FXML
    private void reset(MouseEvent event) {

    }

    @FXML
    private void initialize() {
        type_transaction.getItems().addAll("Vente", "Location", "Echange");
        typeBien.getItems().addAll("Maison", "Appartement", "Terrain");
        wilaya.getItems().addAll(Wilaya.getWilayas());
    }
}
