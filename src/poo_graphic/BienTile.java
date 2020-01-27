package poo_graphic;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import poo_graphic.Models.Appartement;
import poo_graphic.Models.Bien;
import poo_graphic.Models.Maison;

import java.io.IOException;

public class BienTile extends ListCell<Bien> {

    public BienTile() {
    }
    @Override
    public void updateItem(Bien bien, boolean empty) {
        super.updateItem(bien, empty);
        if (bien != null) {
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

            Button sendMessage = new Button("send message");
            Button viewDetails = new Button("view details");
            // le bouton capte l'évènement

            sendMessage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {

                }
            });

            HBox child3 = new HBox(sendMessage, viewDetails);
            child3.setSpacing(20);
            child3.setAlignment(Pos.BASELINE_RIGHT);

            VBox bienTile = new VBox(child, child3);
            bienTile.setSpacing(10);
            setGraphic(bienTile);
        } else {
            setGraphic(null);
        }
    }
}
