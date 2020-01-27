package poo_graphic.Models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Date;

public class Appartement extends Habitable{
    private IntegerProperty etage;
    private BooleanProperty estDuplex, contientAscenseur;

    public Appartement(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, int nbrPieces, boolean estMeuble, int etage, boolean estDuplex, boolean contientAscenseur) throws DuplexException{
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl, nbrPieces, estMeuble);
        this.etage = new SimpleIntegerProperty(etage);
        if (nbrPieces == 1 && estDuplex)
            throw new DuplexException();
        this.estDuplex = new SimpleBooleanProperty(estDuplex);
        this.contientAscenseur = new SimpleBooleanProperty(contientAscenseur);
    }

    public Appartement(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, int nbrPieces, boolean estMeuble, int etage, boolean estDuplex, boolean contientAscenseur, Wilaya wilayaEchange) throws DuplexException{
        super(addresse, wilaya, typeTransaction, proprietaire, date, superficie, prix, negociable, description, imgsUrl, wilayaEchange, nbrPieces, estMeuble);
        this.etage = new SimpleIntegerProperty(etage);
        if (nbrPieces == 1 && estDuplex)
            throw new DuplexException();
        this.estDuplex = new SimpleBooleanProperty(estDuplex);
        this.contientAscenseur = new SimpleBooleanProperty(contientAscenseur);
    }


    public int getEtage() {
        return etage.getValue();
    }

    public boolean isEstDuplex() {
        return estDuplex.getValue();
    }

    public boolean isContientAscenseur() {
        return contientAscenseur.getValue();
    }

    @Override
    protected double prixVente() {
        return ( super.prixVente() + ( (this.etage.getValue() >= 0 && this.etage.getValue() <=2) ? 50000 : 0 ) );
    }

    @Override
    protected double prixLocation() {
        double frais1 = (this.etage.getValue() >= 0 && this.etage.getValue() <=2) ? 5000 : 0 ; // frais dans le cas l'appartement est entre 0 et 2eme etage
        double frais2 = ( !this.contientAscenseur.getValue() && this.etage.getValue() >=6 ) ? 500 : 0;
        return super.prixLocation() + frais1 - frais2 * this.getSuperficie();
    }

//    public void afficher() {
//        System.out.println("Le bien est un appartement : ");
//        super.afficher();
//    }
//
//    public void afficherDetails() {
//        super.afficherDetails();
//        System.out.println("le nombre d'etage est : "+this.etage);
//        System.out.println("l'appartement est duplexe : "+this.estDuplex);
//        System.out.println("le prix finale du bien est : "+this.calculerPrix());
//    }
}
