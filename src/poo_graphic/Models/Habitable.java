package poo_graphic.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Date;

abstract public class Habitable extends Bien{
    private IntegerProperty nbrPieces;
    private BooleanProperty estMeuble;

    public Habitable(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, int nbrPieces, boolean estMeuble) {
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl);
        this.nbrPieces = new SimpleIntegerProperty(nbrPieces);
        this.estMeuble = new SimpleBooleanProperty(estMeuble);
    }

    public Habitable(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, Wilaya wilayaEchange, int nbrPieces, boolean estMeuble) {
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl, wilayaEchange);
        this.nbrPieces = new SimpleIntegerProperty(nbrPieces);
        this.estMeuble = new SimpleBooleanProperty(estMeuble);
    }

    public int getNbrPieces() {
        return nbrPieces.getValue();
    }

    public boolean isEstMeuble() {
        return estMeuble.getValue();
    }

//    public void afficherDetails() {
//        super.afficherDetails();
//        System.out.println("le nombre de pieces du bien est : " + nbrPieces);
//        System.out.println("le bien est meublé : " + estMeuble);
//    }
}
