package poo_graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import poo_graphic.Models.Agence;
import poo_graphic.Models.Bien;

import java.util.function.Function;


public class SendMessageController {
    private Agence immoEsi;
    private Bien bien;

    @FXML
    private TextArea message;
    @FXML
    private Label messageSuccee;

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;
    }
    public void setBien(Bien b) {
        bien = b;
    }

    @FXML
    private void send(MouseEvent event) {
        String msg = message.getText();
        if (msg.equals("")) {
            messageSuccee.setText("Veuillez introduire le message");
            messageSuccee.setTextFill(Color.web("#e51616"));
        } else {
            immoEsi.envoyerMessage(bien, message.getText());
            messageSuccee.setText("Votre message a été envoyé");
            messageSuccee.setTextFill(Color.web("#40cda9"));
        }
    }

    @FXML
    private void quitter(MouseEvent event) {
        Stage stage = (Stage) message.getScene().getWindow();

        stage.close();
    }
}
