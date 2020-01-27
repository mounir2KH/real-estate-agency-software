package poo_graphic;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poo_graphic.Models.Agence;
import poo_graphic.Models.Bien;

import java.util.function.Function;


public class SendMessageController {
    private Agence immoEsi;
    private Bien bien;

    @FXML
    private TextArea message;

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;
    }
    public void setBien(Bien b) {
        bien = b;
    }

    @FXML
    private void send(MouseEvent event) {
        immoEsi.envoyerMessage(bien, message.getText());
    }

    @FXML
    private void quitter(MouseEvent event) {
        Stage stage = (Stage) message.getScene().getWindow();

        stage.close();
    }
}
