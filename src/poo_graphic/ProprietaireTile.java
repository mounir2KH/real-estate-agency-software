package poo_graphic;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import poo_graphic.Models.Appartement;
import poo_graphic.Models.Bien;
import poo_graphic.Models.Maison;
import poo_graphic.Models.Proprietaire;

public class ProprietaireTile extends ListCell<Proprietaire> {

    public ProprietaireTile() {
    }
    @Override
    public void updateItem(Proprietaire proprietaire, boolean empty) {
        super.updateItem(proprietaire, empty);
        if (proprietaire != null) {
            String typeString = "Terrain";
            Text nom = new Text("Nom : " + proprietaire.getNom());
            Text prenom = new Text("Prenom : " + proprietaire.getPrenom());
            Text addresse = new Text("Addresse : " + proprietaire.getAddresse());
            Text tel = new Text("Tel : " + proprietaire.getTel());

            VBox bienTile = new VBox(nom, prenom, addresse, tel);
            bienTile.setSpacing(10);
            setGraphic(bienTile);
        } else {
            setGraphic(null);
        }
    }
}
