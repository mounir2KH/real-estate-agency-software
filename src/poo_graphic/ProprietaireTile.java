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
        if (proprietaire != null && proprietaire.getBiens().size() > 0) {
            Bien bien = proprietaire.getBiens().get(0);
            Text price = new Text(Double.toString(bien.calculerPrix()) + "$");
            price.setTextAlignment(TextAlignment.RIGHT);
            String typeString = "Terrain";
            if (bien instanceof Maison) {
                typeString = "Maison";
            } else if (bien instanceof Appartement) {
                typeString = "Appartement";
            }
            Text type = new Text(typeString);
            type.setTextAlignment(TextAlignment.LEFT);
            BorderPane child1 = new BorderPane();
            child1.setLeft(type);
            child1.setRight(price);

            Label child2 = new Label(bien.getTypeTransaction().toString());
            VBox child = new VBox(child1, child2);
            Label wilaya = new Label(bien.getWilaya().getNomWilaya()) ;

            VBox bienTile = new VBox(child, wilaya);
            bienTile.setSpacing(10);
            setGraphic(bienTile);
        } else {
            setGraphic(null);
        }
    }
}
