package poo_graphic;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import poo_graphic.Models.*;

public class DetailsController {
    @FXML
    protected TableColumn<Bien, String> bienColumn;
    @FXML
    protected Button log;
    @FXML
    protected Label erreurSendMessage;
    @FXML
    protected Label addresse, description, prix, typeTransaction, negociable, superficie, date, proprietaire, nbrPieces, meuble, nbrEtage, booleenValue;
    @FXML
    protected Text piece_facade, meuble_statut, etage, booleen;
//    @FXML
//    private void initialize() {
//        list_biens.setCellFactory((ListView<Bien> l) -> new BienTile());
//    }



    @FXML
    protected void afficherBienDetail(Bien bien) {
        erreurSendMessage.setText("");
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
                etage.setText("nombre d'etage :");
                nbrPieces.setText(Integer.toString(((Habitable) bien).getNbrPieces()));
                meuble.setText((((Habitable) bien).isEstMeuble()) ? "Oui" : "Non");
                if (bien instanceof Appartement) {
                    nbrEtage.setText(Integer.toString(((Appartement) bien).getEtage()));
                    String s = (((Appartement) bien).isEstDuplex()) ? "Oui" : "Non";
                    s += (((Appartement) bien).isContientAscenseur()) ? "/Oui" : "/Non";
                    booleenValue.setText(s);
                    booleen.setText("duplex / ascenceur");
                } else {
                    nbrEtage.setText(Integer.toString(((Maison) bien).getNbrEtages()));
                    String s = (((Maison) bien).isContientGarage()) ? "Oui" : "Non";
                    s += (((Maison) bien).isContientJardin()) ? "/Oui" : "/Non";
                    s += (((Maison) bien).isContientPiscine()) ? "/Oui" : "/Non";
                    booleenValue.setText(s);
                    booleen.setText("Garage / Jardin / Piscine");
                }
            }
            if (bien instanceof Terrain) {
                int nbrFacades = ((Terrain) bien).getNbrFacade();
                nbrPieces.setText(Integer.toString(nbrFacades));
                piece_facade.setText("Nombre de facades :");
                meuble_statut.setText("Statut :");
                meuble.setText(((Terrain) bien).getStatutJuridique());
                nbrEtage.setText("");
                etage.setText("");
                booleen.setText("");
                booleenValue.setText("");
            }
        } else {
            addresse.setText("");
            description.setText("");
            prix.setText("");
            typeTransaction.setText("");
            negociable.setText("");
            superficie.setText("");
            date.setText("");
            proprietaire.setText("");
            nbrPieces.setText("");
            meuble.setText("");
            nbrEtage.setText("");
            booleenValue.setText("");
        }
    }
}
