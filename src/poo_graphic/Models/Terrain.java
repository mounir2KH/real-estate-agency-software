package poo_graphic.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Terrain extends Bien{
    private IntegerProperty nbrFacade;
    private StringProperty statutJuridique;

    public Terrain(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, int nbrFacade, String statutJuridique) {
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl);
        this.nbrFacade = new SimpleIntegerProperty(nbrFacade);
        this.statutJuridique = new SimpleStringProperty(statutJuridique);
    }

    public Terrain(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, int nbrFacade, String statutJuridique, Wilaya wilayaEchange) {
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl, wilayaEchange);
        this.nbrFacade = new SimpleIntegerProperty(nbrFacade);
        this.statutJuridique = new SimpleStringProperty(statutJuridique);
    }

    public int getNbrFacade() {
        return nbrFacade.getValue();
    }

    public String getStatutJuridique() {
        return statutJuridique.getValue();
    }

    @Override
    protected double prixVente() {
        double resultat = super.prixVente() + ( (this.nbrFacade.getValue() > 1) ? ( 0.005 * this.getPrix() * this.nbrFacade.getValue() ) : 0 );
        return resultat;
    }

//    public void afficher() {
//        System.out.println("Le bien est un terrain : ");
//        super.afficher();
//    }
//
//    public void afficherDetails() {
//        super.afficherDetails();
//        System.out.println("le statut juridique est : "+this.statutJuridique);
//        System.out.println("le nombre de facade est : "+this.nbrFacade);
//        System.out.println("le prix finale du bien est : " + this.calculerPrix());
//    }
}
