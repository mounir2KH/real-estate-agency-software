package poo_graphic;

import poo_graphic.Models.Agence;

public class ArchivesController extends ListBiensController{
    @Override
    public void setImmoEsi(Agence immoEsi) {
        super.setImmoEsi(immoEsi);
        list_biens.setItems(immoEsi.getArchives());
    }
}
