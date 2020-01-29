package poo_graphic;

import poo_graphic.Models.*;

import java.util.Date;

public class modifierBienController extends ajouterBienController {
    Bien ancienBien;

    @Override
    protected void fin(Bien b) {
        immoEsi.supprimerBien(ancienBien);
        immoEsi.ajouterBien(b);
    }

    @Override
    protected Date getDate() {
        return ancienBien.getDate();
    }

    public void setAncienBien(Bien ancienBien) {
        this.ancienBien = ancienBien;
    }

    @Override
    protected void initializer() {
        super.initializer();
        wilayas.getSelectionModel().select("wilaya1");
        wilayas.getSelectionModel().select(ancienBien.getWilaya().getNomWilaya());
        proprietaires.getSelectionModel().select(ancienBien.getProprietaire());
        Transactions.getSelectionModel().select(ancienBien.getTypeTransaction());
        prixMin.setText(Double.toString(ancienBien.getPrix()));
        addresse.setText(ancienBien.getAddresse().getValue());
        Description.setText(ancienBien.getDescription());
        ouinego.setSelected(ancienBien.isNegociable());
        superficies.setText(Double.toString(ancienBien.getSuperficie()));
        if (ancienBien instanceof Habitable) {
            numpieces.setText(Integer.toString(((Habitable) ancienBien).getNbrPieces()));
            ouimeuble.setSelected(((Habitable) ancienBien).isEstMeuble());
            if (ancienBien instanceof Maison) {
                Bientype.getSelectionModel().select("Maison");
                ouigarage.setSelected(((Maison) ancienBien).isContientGarage());
                ouijardin.setSelected(((Maison) ancienBien).isContientJardin());
                ouipiscine.setSelected(((Maison) ancienBien).isContientPiscine());
                habitables.setText(Double.toString(((Maison) ancienBien).getSuperficieHabitable()));
                nombretage.setText(Integer.toString(((Maison) ancienBien).getNbrEtages()));
            } else {
                Bientype.getSelectionModel().select("Appartement");
                ouiascenceur.setSelected(((Appartement) ancienBien).isContientAscenseur());
                ouiduplexe.setSelected(((Appartement) ancienBien).isEstDuplex());
                etages.setText(Integer.toString(((Appartement) ancienBien).getEtage()));
            }
        } else {
            Bientype.getSelectionModel().select("Terrain");
            facades.setText(Integer.toString(((Terrain) ancienBien).getNbrFacade()));
            Juridique.setText(((Terrain) ancienBien).getStatutJuridique());
        }
        Bientype.setDisable(true);
        addresse.setDisable(true);
    }
}
