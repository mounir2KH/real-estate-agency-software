package poo_graphic;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import poo_graphic.Models.Agence;
import poo_graphic.Models.Wilaya;
import poo_graphic.Models.*;
import poo_graphic.Models.Transaction;

import java.util.Date;
import java.util.LinkedList;


public class ajouterBienController {
    protected Agence immoEsi;
    @FXML
    protected ComboBox<String> echange_wilaya;

    @FXML
    protected ComboBox<Proprietaire> proprietaires;

    @FXML
    protected ToggleGroup Negociable;

    @FXML
    protected ComboBox<String> wilayas;

    @FXML
    protected TextArea Description;

    @FXML
    protected TextField prixMin;

    @FXML
    protected TextField superficies;
    @FXML
    protected Label erreur;
//
//    @FXML
//    private Group meublé;
//
//    @FXML
//    private ToggleGroup Meublé;

    @FXML
    protected TextField prixMin1;

//    @FXML
//    private Group jardin;
//
//    @FXML
//    private ToggleGroup Jardin;

//    @FXML
//    private Group piscine;
//
//    @FXML
//    private ToggleGroup Piscine;
//
//    @FXML
//    private Group ascenceur;
//
//    @FXML
//    private ToggleGroup Ascenceurs;

    @FXML
    protected TextField habitables;

    @FXML
    protected TextField nombretage;

//    @FXML
//    private Group duplexe;
//
//    @FXML
//    private ToggleGroup Duplexe;
//
//    @FXML
//    private ToggleGroup Jardin1;

    @FXML
    protected TextField etages;

    @FXML
    protected TextField numpieces;

//    @FXML
//    private Group garage;
//
//    @FXML
//    private ToggleGroup Garage;

    @FXML
    protected TextField facades;

    @FXML
    protected TextField Juridique, addresse;
    @FXML
    protected CheckBox ouigarage,ouijardin,ouipiscine,ouimeuble,ouiascenceur,ouiduplexe,ouinego;
    @FXML
    protected ComboBox<Transaction> Transactions;
    @FXML
    protected ComboBox<String> Bientype;

    @FXML
    private void Transa() {
        if (Transactions.getValue() == Transaction.Echange) {
            echange_wilaya.setDisable(false);
        } else {
            echange_wilaya.setDisable(true);
        }
    }

    @FXML
    private void biensact() {
        switch (Bientype.getSelectionModel().getSelectedItem()) {

            case "Maison":
                ouimeuble.setDisable(false);
                ouijardin.setDisable(false);
                ouipiscine.setDisable(false);
                ouipiscine.setDisable(true);
                habitables.setDisable(false);
                nombretage.setDisable(false);
                ouiduplexe.setDisable(true);
                etages.setDisable(false);
                ouigarage.setDisable(false);
                facades.setDisable(true);
                Juridique.setDisable(true);
                numpieces.setDisable(false);
                etages.setDisable(true);
                break;
            case "Appartement":
                ouimeuble.setDisable(false);
                ouijardin.setDisable(true);
                ouipiscine.setDisable(true);
                ouiascenceur.setDisable(false);
                habitables.setDisable(true);
                nombretage.setDisable(true);
                ouiduplexe.setDisable(false);
                ouigarage.setDisable(true);
                facades.setDisable(true);
                Juridique.setDisable(true);
                numpieces.setDisable(false);
                break;
            case "Terrain":
                ouimeuble.setDisable(true);
                etages.setDisable(true);
                ouijardin.setDisable(true);
                ouipiscine.setDisable(true);
                ouiascenceur.setDisable(true);
                habitables.setDisable(true);
                nombretage.setDisable(true);
                ouiduplexe.setDisable(true);
                ouigarage.setDisable(true);
                facades.setDisable(false);
                Juridique.setDisable(false);
                numpieces.setDisable(true);
                break;
        }
    }

    protected void initializer() {
        wilayas.getItems().addAll(Wilaya.getWilayas());
        echange_wilaya.getItems().addAll(Wilaya.getWilayas());
        Transactions.getItems().addAll(Transaction.values());
        Bientype.getItems().addAll("Appartement", "Maison", "Terrain");
        proprietaires.getItems().addAll(immoEsi.getProprietaires());
    }

    @FXML
    void add(MouseEvent event) throws DuplexException {
        try {
            double superficie, prix, habitable;
            int numpiece, numetages, numfacades, etage, nbrpices;
            Transaction transact;
            String type, statut, wilaya, wilaya_ech, adress, description;
            Proprietaire propr;
            boolean contientpiscine, meuble, contientgarage, contientjardin, contientascenceur, negociable, duplexe;
            Date d = getDate();
            transact = Transactions.getSelectionModel().getSelectedItem();
            Bien bien;
            if (transact == null)
                throw new Exception();
            propr = proprietaires.getSelectionModel().getSelectedItem();
            if (propr == null)
                throw new Exception();
            if (!prixMin.getText().equals("")) {
                prix = Double.parseDouble(prixMin.getText());
            } else
                throw new Exception();
            if (!superficies.getText().equals("")) {
                superficie = Double.parseDouble(superficies.getText());
            } else
                throw new Exception();
            description = Description.getText();
            wilaya = wilayas.getSelectionModel().getSelectedItem();

            if (!addresse.getText().equals("")) {
                adress = addresse.getText();
            } else
                throw new Exception();
            negociable = ouinego.isSelected();

            switch (Bientype.getSelectionModel().getSelectedItem()) {
                case "Maison":
                    contientgarage = ouigarage.isSelected();
                    contientjardin = ouijardin.isSelected();
                    meuble = ouimeuble.isSelected();
                    contientpiscine = ouipiscine.isSelected();
                    if (!numpieces.getText().equals(""))
                        numpiece = Integer.parseInt(numpieces.getText());
                    else
                        throw new Exception();
                    if (!habitables.getText().equals(""))
                        habitable = Double.parseDouble(habitables.getText());
                    else
                        throw new Exception();
                    if (!nombretage.getText().equals(""))
                        etage = Integer.parseInt(nombretage.getText());
                    else
                        throw new Exception();

                    if (transact != Transaction.Echange)
                        bien = new Maison(adress, Wilaya.getWilaya(wilaya), transact, propr, d, superficie, prix, negociable, description, null, numpiece, meuble, etage, contientjardin, contientgarage, contientpiscine, habitable);
                    else {
                        wilaya_ech = echange_wilaya.getSelectionModel().getSelectedItem();
                        if (wilaya_ech.equals(""))
                            wilaya_ech = wilaya;
                        bien = new Maison(adress, Wilaya.getWilaya(wilaya), transact, propr, new Date(System.currentTimeMillis()), superficie, prix, negociable, description, null, numpiece, meuble, etage, contientjardin, contientgarage, contientpiscine, habitable, Wilaya.getWilaya(wilaya_ech));
                    }
                    break;
                case "Appartement":
                    duplexe = ouiduplexe.isSelected();
                    contientascenceur = ouiascenceur.isSelected();
                    meuble = ouimeuble.isSelected();
                    if (!numpieces.getText().equals(""))
                        numpiece = Integer.parseInt(numpieces.getText());
                    else
                        throw new Exception();
                    if (!etages.getText().equals(""))
                        numetages = Integer.parseInt(etages.getText());
                    else
                        throw new Exception();

                    if (transact != Transaction.Echange)
                        bien = new Appartement(adress, Wilaya.getWilaya(wilaya), transact, propr, d, superficie, prix, negociable, description, null, numpiece, meuble, numetages, duplexe, contientascenceur);
                    else {
                        wilaya_ech = echange_wilaya.getSelectionModel().getSelectedItem();
                        if (wilaya_ech.equals(""))
                            wilaya_ech = wilaya;
                        bien = new Appartement(adress, Wilaya.getWilaya(wilaya), transact, propr, d, superficie, prix, negociable, description, null, numpiece, meuble, numetages, duplexe, contientascenceur, Wilaya.getWilaya(wilaya_ech));
                    }
                    break;
                default:
                    if (!facades.getText().equals(""))
                        numfacades = Integer.parseInt(facades.getText());
                    else
                        throw new Exception();
                    statut = Juridique.getText();
                    if (transact != Transaction.Echange)
                        bien = new Terrain(adress, Wilaya.getWilaya(wilaya), transact, propr, d, superficie, prix, negociable, description, null, numfacades, statut);
                    else {
                        wilaya_ech = echange_wilaya.getSelectionModel().getSelectedItem();
                        if (wilaya_ech.equals(""))
                            wilaya_ech = wilaya;
                        bien = new Terrain(adress, Wilaya.getWilaya(wilaya), transact, propr, d, superficie, prix, negociable, description, null, numfacades, statut, Wilaya.getWilaya(wilaya_ech));
                    }
                    break;
            }
            fin(bien);
            Stage stage = (Stage) prixMin.getScene().getWindow();
            stage.close();
        } catch (SuperficieException err) {
            erreur.setText("La superficie habitable est superieure a la surface totale");
        } catch (DuplexException err) {
            erreur.setText("Un appartement F1 ne peut pas etre un duplex");
        }catch (Exception err) {
            erreur.setText("Veuillez remplir les champs necessaires");
        }

    }

    protected void fin(Bien b) {
        immoEsi.ajouterBien(b);
    }

    protected Date getDate() {
        return new Date(System.currentTimeMillis());
    }

    @FXML
    private void quitter(MouseEvent event) {
        Stage stage = (Stage) prixMin.getScene().getWindow();

        stage.close();
    }

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;
        initializer();
    }

}
