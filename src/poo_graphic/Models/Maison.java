package poo_graphic.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Date;

public class Maison extends Habitable{
    private IntegerProperty nbrEtages;
    private BooleanProperty contientJardin, contientGarage, contientPiscine;
    private double superficieHabitable;

    public Maison(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, int nbrPieces, boolean estMeuble, int nbrEtages, boolean contientJardin, boolean contientGarage, boolean contientPiscine, double superficieHabitable) throws SuperficieException{
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl, nbrPieces, estMeuble);
        this.nbrEtages = new SimpleIntegerProperty(nbrEtages);
        this.contientJardin = new SimpleBooleanProperty(contientJardin);
        this.contientGarage = new SimpleBooleanProperty(contientGarage);
        this.contientPiscine = new SimpleBooleanProperty(contientPiscine);
        if (superficie <= superficieHabitable)
            throw new SuperficieException();
        this.superficieHabitable = superficieHabitable;
    }
    public Maison(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl,int nbrPieces, boolean estMeuble, int nbrEtages, boolean contientJardin, boolean contientGarage, boolean contientPiscine, double superficieHabitable, Wilaya wilayaEchange) throws SuperficieException{
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl, wilayaEchange, nbrPieces, estMeuble);
        this.nbrEtages = new SimpleIntegerProperty(nbrEtages);
        this.contientJardin = new SimpleBooleanProperty(contientJardin);
        this.contientGarage = new SimpleBooleanProperty(contientGarage);
        this.contientPiscine = new SimpleBooleanProperty(contientPiscine);
        if (superficie <= superficieHabitable)
            throw new SuperficieException();
        this.superficieHabitable = superficieHabitable;
    }

    public int getNbrEtages() {
        return nbrEtages.getValue();
    }

    public boolean isContientJardin() {
        return contientJardin.getValue();
    }

    public boolean isContientGarage() {
        return contientGarage.getValue();
    }

    public boolean isContientPiscine() {
        return contientPiscine.getValue();
    }

    public double getSuperficieHabitable() {
        return superficieHabitable;
    }


    @Override
    protected double prixVente() {
        return super.prixVente() + ( (this.contientGarage.getValue() || this.contientJardin.getValue() || this.contientPiscine.getValue()) ? ( this.getPrix() * 0.005 ) : 0 );
    }

    @Override
    protected double prixLocation() {
        return super.prixLocation() + ( (this.contientPiscine.getValue()) ? 50000 : 0 );
    }

//    public void afficher() {
//        System.out.println("Le bien est une maison : ");
//        super.afficher();
//    }
//    public void afficherDetails() {
//        super.afficherDetails();
//        System.out.println("le bien contient un garage : "+this.contientGarage);
//        System.out.println("le bien contient un jardin  : "+this.contientJardin);
//        System.out.println(("le bin e contient une piscine : "+this.contientPiscine));
//        System.out.println("le nombre d'etage est : "+this.nbrEtages);
//        System.out.println("la superficie habitable est de : "+this.superficieHabitable);
//        System.out.println("le prix finale du bien est : " + this.calculerPrix());
//    }
}
