package poo_graphic;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import poo_graphic.Models.Agence;
import poo_graphic.Models.Bien;
import poo_graphic.Models.Proprietaire;

public class ProprietairesController extends ListBiensController {

    @FXML
    ListView<Proprietaire> list_propritaires;

    @Override
    public void setImmoEsi(Agence immoEsi) {
        super.setImmoEsi(immoEsi);
        list_propritaires.setItems(immoEsi.getProprietaires());
        list_propritaires.setCellFactory((ListView<Proprietaire> l) -> new ProprietaireTile());

        list_propritaires.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Proprietaire>() {
            @Override
            public void onChanged(Change<? extends Proprietaire> change) {
                Proprietaire p = list_propritaires.getSelectionModel().getSelectedItem();
                list_biens.setItems(p.getBiens());
            }
        });
    }
}
