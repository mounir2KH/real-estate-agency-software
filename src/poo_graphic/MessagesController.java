package poo_graphic;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import poo_graphic.Models.Agence;
import poo_graphic.Models.Bien;

public class MessagesController extends DetailsController {

    private Agence immoEsi;

    @FXML
    ListView<Agence.Message> list_messages;

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;

        list_messages.setItems(immoEsi.getMessages());
        list_messages.setCellFactory((ListView<Agence.Message> l) -> new MessageTile());

        list_messages.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Agence.Message>() {
            @Override
            public void onChanged(Change<? extends Agence.Message> change) {
                afficherBienDetail(list_messages.getSelectionModel().getSelectedItem().getBien());
            }
        });
    }
}
