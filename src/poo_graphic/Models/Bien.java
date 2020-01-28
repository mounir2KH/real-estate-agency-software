package poo_graphic.Models;

import javafx.beans.property.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


abstract public class Bien implements Comparable<Bien>{
    private StringProperty addresse;
    private ObjectProperty<Wilaya> wilaya, wilayaEchange;
    private ObjectProperty<Transaction> typeTransaction;
    private ObjectProperty<Proprietaire> proprietaire;
    private ObjectProperty<Date> date;
    private DoubleProperty superficie, prix;
    private BooleanProperty negociable;
    private StringProperty description;
    private String[] imgsUrl;

    public Bien(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl) {
        this.addresse = new SimpleStringProperty(addresse);
        this.wilaya = new SimpleObjectProperty<>(wilaya);
        this.typeTransaction = new SimpleObjectProperty<>(typeTransaction);
        this.proprietaire = new SimpleObjectProperty<>(proprietaire);
        this.date = new SimpleObjectProperty<>(date);
        this.superficie = new SimpleDoubleProperty(superficie);
        this.prix = new SimpleDoubleProperty(prix);
        this.negociable = new SimpleBooleanProperty(negociable);
        this.description = new SimpleStringProperty(description);
        this.imgsUrl = imgsUrl;

        proprietaire.ajouterBien(this);
    }

    public Bien(String addresse, Wilaya wilaya, Transaction typeTransaction, Proprietaire proprietaire, Date date, double superficie, double prix, boolean negociable, String description, String[] imgsUrl, Wilaya wilayaEchange) {
        this.addresse = new SimpleStringProperty(addresse);
        this.wilaya = new SimpleObjectProperty<>(wilaya);
        this.typeTransaction = new SimpleObjectProperty<>(typeTransaction);
        this.proprietaire = new SimpleObjectProperty<>(proprietaire);
        this.date = new SimpleObjectProperty<>(date);
        this.superficie = new SimpleDoubleProperty(superficie);
        this.prix = new SimpleDoubleProperty(prix);
        this.negociable = new SimpleBooleanProperty(negociable);
        this.description = new SimpleStringProperty(description);;
        this.imgsUrl = imgsUrl;
        this.wilayaEchange = new SimpleObjectProperty<>(wilayaEchange);

        proprietaire.ajouterBien(this);
    }

    public Wilaya getWilaya() {
        return wilaya.getValue();
    }

    public StringProperty getAddresse() {
        return addresse;
    }

    public Transaction getTypeTransaction() {
        return typeTransaction.getValue();
    }

    public Proprietaire getProprietaire() {
        return proprietaire.getValue();
    }

    public Date getDate() {
        return date.getValue();
    }

    public double getSuperficie() {
        return superficie.getValue();
    }

    public double getPrix() {
        return prix.getValue();
    }

    public void setAddresse(String addresse) {
        this.addresse.set(addresse);
    }

    public void setWilaya(Wilaya wilaya) {
        this.wilaya.setValue(wilaya);
    }

    public void setWilayaEchange(Wilaya wilayaEchange) {
        this.wilayaEchange.setValue(wilayaEchange);
    }

    public void setTypeTransaction(Transaction typeTransaction) {
        this.typeTransaction.setValue(typeTransaction);
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire.setValue(proprietaire);
    }

    public void setDate(Date date) {
        this.date.setValue(date);
    }

    public void setSuperficie(double superficie) {
        this.superficie.setValue(superficie);
    }

    public void setPrix(double prix) {
        this.prix.setValue(prix);
    }

    public void setNegociable(boolean negociable) {
        this.negociable.setValue(negociable);
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public void setImgsUrl(String[] imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public boolean isNegociable() {
        return negociable.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public String[] getImgsUrl() {
        return imgsUrl;
    }

    public double prixMetreCarre() {
        return prix.getValue() / superficie.getValue();
    }


    public double calculerPrix() {
        double resultat = 0;
        switch (this.getTypeTransaction()) {
            case Vente:
                resultat = this.prixVente();
                break;
            case Echange:
                resultat = this.prixVente();
                if (this.wilayaEchange != null && !this.wilaya.getValue().getNomWilaya().equals(this.wilayaEchange.getValue().getNomWilaya()))
                    resultat += this.getPrix() * 0.0025;
                break;
            case Location:
                resultat = this.prixLocation();
                break;
        }
        return resultat;
    }

    protected double prixVente() {
        double resultat = prix.getValue();
        if (this.prix.getValue() < 5000000) {
            if (this.wilaya.getValue().getPrixMetreCarre() < 50000) {
                resultat += prix.getValue() * 0.03;
            } else {
                resultat += prix.getValue() * 0.035;
            }
        } else if (this.prix.getValue() >= 5000000 && this.prix.getValue() < 15000000) {
            if (this.wilaya.getValue().getPrixMetreCarre() < 50000) {
                resultat += prix.getValue() * 0.02;
            } else {
                resultat += prix.getValue() * 0.025;
            }
        } else if (this.prix.getValue() >= 15000000) {
            if (this.wilaya.getValue().getPrixMetreCarre() < 70000) {
                resultat += prix.getValue() * 0.01;
            } else {
                resultat += prix.getValue() * 0.02;
            }
        }
        return resultat;
    }
    protected double prixLocation() {
        double resultat = prix.getValue();

        if (this.superficie.getValue() < 60) {
            if (this.wilaya.getValue().getPrixMetreCarre() < 50000) {
                resultat += prix.getValue() * 0.01;
            } else {
                resultat += prix.getValue() * 0.015;
            }
        } else if (this.superficie.getValue() >= 60 && this.superficie.getValue() < 150) {
            if (this.wilaya.getValue().getPrixMetreCarre() < 50000) {
                resultat += prix.getValue() * 0.02;
            } else {
                resultat += prix.getValue() * 0.025;
            }
        } else if (this.superficie.getValue() >= 150) {
            if (this.wilaya.getValue().getPrixMetreCarre() < 50000) {
                resultat += prix.getValue() * 0.03;
            } else {
                resultat += prix.getValue() * 0.035;
            }
        }

        return resultat;
    }

//    public void afficher() {
//        System.out.println("adresse : "+ this.addresse);
//        System.out.println("description : " + this.description);
//        System.out.println("prix du proprietaire : " + this.prix );
//        System.out.println("nature de transaction : "+ this.typeTransaction);
//    }

//    public void afficherDetails() {
//        this.afficher();
//        System.out.println("le bien est negociable : "+ this.negociable);
//        System.out.println("la superficie du bien est  : "+ this.superficie);
//        System.out.println("le bien se siture a :  : "+this.wilaya.getNomWilaya());
//        System.out.println("le bien a ete ajoute le : "+this.date);
//        System.out.println("le proprietaire de ce bien est : "+this.proprietaire.getNom());
//        System.out.println("l'image du bien : "+ Arrays.toStringProperty(this.imgsUrl));
//    }

    public void afficherPrixFinal() {
        System.out.println("Prix final du bien : " + this.calculerPrix());
    }

    @Override
    public int compareTo(Bien bien) {
        int  i = this.date.getValue().compareTo(bien.getDate());
        return -i;
    }


    public boolean equals(Bien obj) {
        return this.addresse.equals(obj.getAddresse());
    }
}
