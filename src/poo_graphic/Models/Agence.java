package poo_graphic.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Agence {
//    private List<Bien> biens = new LinkedList<Bien>();
    private ObservableList<Bien> biens = FXCollections.observableArrayList();
//    private List<Bien> archives = new LinkedList<>();
    private ObservableList<Bien> archives = FXCollections.observableArrayList();
//    private List<Proprietaire> proprietaires = new LinkedList<>();
    private ObservableList<Proprietaire> proprietaires = FXCollections.observableArrayList();

    private boolean isAuth = false;

    private StringProperty password = new SimpleStringProperty("esi");

    public boolean login(String passowrd) {
        if (this.password.getValue().equals(passowrd)) {
            isAuth = true;
            return true;
        } else {
            System.err.println("Votre mot de passe est INCORRECTE");
            return false;
        }
    }
    public void deconnecter() {
        isAuth = false;
    }

    public Bien getBien(int i) {
        if (!isAuth)
            return null;
        if (i < 0 || i >= this.biens.size()) {
            System.err.println("L'indice donnee est hors la taille de la liste");
            return null;
        }
        return this.biens.get(i);
    }

    public ObservableList<Proprietaire> getProprietaires() {
        return proprietaires;
    }

    public ObservableList<Bien> getBiens() {
        return biens;
    }

    public ObservableList<Bien> getArchives() {
        return archives;
    }

    public void ajouterBien(Bien bien) {
        if (!isAuth)
            return;
        Proprietaire proprietaire = bien.getProprietaire();

        for (Bien b : biens) {
            if (b.equals(bien))
                return;
        }

        if (!this.proprietaires.contains(proprietaire)) {
            this.proprietaires.add(proprietaire);
        }

        this.biens.add(bien);
        this.biens.sort(Bien::compareTo);
    }

    public void modifierBien(int i, Bien bien) {
        if (!isAuth)
            return;
        if (i < 0 || i >= this.biens.size()) {
            System.err.println("L'indice donnee est hors la taille de la liste");
            return;
        }
        this.biens.set(i, bien);
    }

    public Bien supprimerBien(int i) {
        if (!isAuth)
            return null;
        if (i < 0 || i >= this.biens.size()) {
            System.err.println("L'indice donnee est hors la taille de la liste");
            return null;
        }
        Bien bien = this.biens.get(i);
        int indice = this.proprietaires.indexOf(bien.getProprietaire());

        if (indice != -1) {
            Proprietaire proprietaire = this.proprietaires.get(indice);
            int indiceBien = proprietaire.getBiens().indexOf(bien);
            if (indiceBien != -1) {
                proprietaire.getBiens().remove(indiceBien);
            }
            if (proprietaire.getBiens().isEmpty()) {
                this.proprietaires.remove(indice);
            }
        }

        return this.biens.remove(i);
    }

//    public void afficherBiens() {
//        int i = 1;
//        for (Bien bien : biens) {
//            System.out.println("Le bien numero : " + i);
//            bien.afficher();
//            System.out.println("-------------------------------------------------");
//            i++;
//        }
//    }

//    public void afficherDetails(int i) {
//        if (i < 0 || i >= this.biens.size()) {
//            System.err.println("L'indice donnee est hors la taille de la liste");
//            return;
//        }
//        this.biens.get(i).afficherDetails();
//    }

    public void afficherPrixFinaux() {
        int i = 1;
        for (Bien bien : biens) {
            System.out.println("Bien numero " + i + " : ");
            bien.afficherPrixFinal();
            i++;
        }
    }

    public void afficherPrixFinal(int i) {
        if (i < 0 || i >= this.biens.size()) {
            System.err.println("L'indice donnee est hors la taille de la liste");
            return;
        }
        this.biens.get(i).afficherPrixFinal();
    }
    public void ajouterPropritetaire(Proprietaire proprietaire) {
        if (!isAuth)
            return;
        if (proprietaire == null)
            return;
        this.proprietaires.add(proprietaire);
    }

    public void afficherProprietaires() {
        if (!isAuth)
            return;
        int i = 1;
        for (Proprietaire proprietaire : proprietaires) {
            System.out.println("Propritetaire numero : " + i);
            proprietaire.afficher();
            System.out.println("-------------------------------------------------");
            i++;
        }
    }

//    public void afficherBiensProprietaire(int indice) {
//        if (!isAuth)
//            return;
//        if (indice < 0 || indice >= this.proprietaires.size()) {
//            System.err.println("L'indice donnee est hors la taille de la liste");
//            return;
//        }
//        int i = 1;
//
//        LinkedList<Bien> bienProprietaire = proprietaires.get(indice).getBiens();
//
//        for (Bien bien : bienProprietaire) {
//            System.out.println("Bien numero : " + i);
//            bien.afficher();
//            System.out.println("-------------------------------------------------");
//            i++;
//        }
//    }

    public void archiver(int i) {
        if (!isAuth)
            return;
        if (i < 0 || i >= this.biens.size()) {
            System.err.println("L'indice donnee est hors la taille de la liste");
            return;
        }
        this.archives.add(this.biens.remove(i));
    }

//    public void afficherBiensArchives() {
//        for (Bien bien : archives) {
//            bien.afficher();
//            System.out.println("-------------------------------------------------");
//        }
//    }
    private boolean bienValide(Bien bien, Transaction transaction, Wilaya wilaya, double prixMin, double prixMax, String type, double superficieMin, int nbrPiecesMin ) {
        if (transaction != null) {
            if (bien.getTypeTransaction() != transaction)
                return false;
        }
        if (wilaya != null) {
            if (!bien.getWilaya().getNomWilaya().equals(wilaya.getNomWilaya()))
                return false;
        }
        if (prixMin != 0) {
            if (bien.calculerPrix() < prixMin)
                return false;
        }
        if (prixMax != 0) {
            if(bien.calculerPrix() > prixMax)
                return false;
        }

        if (!type.equals("")) {
            switch (type) {
                case "Maison":
                    if (!(bien instanceof Maison))
                        return false;
                        break;
                case "Appartement":
                    if (!(bien instanceof Appartement))
                        return false;
                        break;
                case "Terrain":
                    if (!(bien instanceof Terrain))
                        return false;
                        break;
                default:
                    return false;
            }
        }

        if (superficieMin != 0) {
            if (bien.getSuperficie() < superficieMin)
                return false;
        }

        if (nbrPiecesMin != 0 ) {
            if (bien instanceof Habitable)
                if (((Habitable) bien).getNbrPieces() < nbrPiecesMin )
                    return false;
            if (bien instanceof Terrain)
                return false;
        }

        return true;
    }

    public ObservableList<Bien> filtrer(Transaction transaction, Wilaya wilaya, double prixMin, double prixMax, String type, double superficieMin, int nbrPiecesMin ) {

        int i = 1;
        ObservableList<Bien> listFiltre = FXCollections.observableArrayList();

        for (Bien bien : biens) {
            if (bienValide(bien, transaction, wilaya, prixMin, prixMax, type, superficieMin, nbrPiecesMin)) {
//                System.out.println("Le bien numero :" + i);
                listFiltre.add(bien);
//                System.out.println("-------------------------------------------------");
                i++;
            }
        }
        return listFiltre;
    }



    public class Message {
        private Bien bien;
        private StringProperty message;

        public Message(Bien bien, String message) {
            this.bien = bien;
            this.message = new SimpleStringProperty(message);
        }

        public Bien getBien() {
            return bien;
        }

        public StringProperty getMessage() {
            return message;
        }
    }

    ObservableList<Message> messages = FXCollections.observableArrayList();

    public ObservableList<Message> getMessages() {
        return messages;
    }

    public void envoyerMessage(Bien bien, String message) {
        messages.add(new Message(bien, message));
    }

    public void afficherMessages() {
        if (!isAuth)
            return;
        for (Message message : messages) {
            System.out.println("Bien :");
//            message.getBien().afficher();
            System.out.println("Message :");
            System.out.println(message.getMessage());
            System.out.println();
        }
    }
}
