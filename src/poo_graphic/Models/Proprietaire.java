package poo_graphic.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class Proprietaire {
    private StringProperty nom, prenom, adrMail, tel, addresse;
    private ObservableList<Bien> biens = FXCollections.observableArrayList();

    public Proprietaire(String nom, String prenom, String adrMail, String tel, String addresse) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.adrMail = new SimpleStringProperty(adrMail);
        this.tel = new SimpleStringProperty(tel);
        this.addresse = new SimpleStringProperty(addresse);
    }

    public String getNom() {
        return nom.getValue();
    }

    public String getPrenom() {
        return prenom.getValue();
    }

    public String getAdrMail() {
        return adrMail.getValue();
    }

    public String getTel() {
        return tel.getValue();
    }

    public String getAddresse() {
        return addresse.getValue();
    }

    public ObservableList<Bien> getBiens() {
        return biens;
    }

    public void afficher() {
        System.out.println("adresse : "+ this.addresse);
        System.out.println("nom : " + this.nom);
        System.out.println("prenom : " + this.prenom );
    }

    public void ajouterBien(Bien bien) {
        biens.add(bien);
    }

    public void supprimerBein(Bien bien) {
        biens.remove(bien);
    }

//    public void afficherBiens() {
//        for (Bien bien : biens) {
//            bien.afficher();
//            System.out.println("***********************************************");
//        }
//    }


    public boolean equals(Proprietaire obj) {
        if (!((Proprietaire) obj).getAddresse().equals(this.addresse.getValue()))
            return false;
        if (!((Proprietaire) obj).getAdrMail().equals(this.adrMail.getValue()))
            return false;
        if (!((Proprietaire) obj).getNom().equals(this.nom.getValue()))
            return false;
        if (!((Proprietaire) obj).getPrenom().equals(this.prenom.getValue()))
            return false;
        if (!((Proprietaire) obj).getTel().equals(this.tel.getValue()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return  nom.getValue();
    }
}
