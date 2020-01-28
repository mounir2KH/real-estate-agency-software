package poo_graphic;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import poo_graphic.Models.Agence;
import poo_graphic.Models.Appartement;
import poo_graphic.Models.Bien;
import poo_graphic.Models.Maison;

public class MessageTile extends ListCell<Agence.Message> {

    public MessageTile() {
    }
    @Override
    public void updateItem(Agence.Message message, boolean empty) {
        super.updateItem(message, empty);
        if (message != null) {
            Bien bien = message.getBien();
            Text messageText = new Text("Message : " + message.getMessage().getValue());
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

            VBox bienTile = new VBox(messageText, child);
            bienTile.setSpacing(10);
            setGraphic(bienTile);
        } else {
            setGraphic(null);
        }
    }
}
