package poo_graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import poo_graphic.Models.*;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {

    Agence immoEsi;

    @Override
    public void start(Stage primaryStage) throws Exception{

        initialize();

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("sample.fxml"));
            GridPane rootLayout = (GridPane) loader.load();
            Controller controller = loader.getController();
            controller.setImmoEsi(immoEsi);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Login");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void initialize() {
        immoEsi = new Agence();

        Wilaya wilaya1 = new Wilaya("wilaya1", 100000);
        Wilaya wilaya2 = new Wilaya("wilaya2", 30000);
        Wilaya wilaya3 = new Wilaya("wilaya3", 60000);

        Proprietaire proprietaire1 = new Proprietaire("prop1", "prenom", "adr@test.com", "xxxx", "wilaya1");
        Proprietaire proprietaire2 = new Proprietaire("prop2", "prenom", "adr@test.com", "xxxx", "wilaya2");
        Proprietaire proprietaire3 = new Proprietaire("prop3", "prenom", "adr@test.com", "xxxx", "wilaya3");
        Proprietaire proprietaire4 = new Proprietaire("prop4", "prenom", "adr@test.com", "xxxx", "wilaya1");

        immoEsi.login("esi");

        immoEsi.ajouterPropritetaire(proprietaire1);
        immoEsi.ajouterPropritetaire(proprietaire2);
        immoEsi.ajouterPropritetaire(proprietaire3);
        immoEsi.ajouterPropritetaire(proprietaire4);
        try {
            Bien bien;
            Date d = new Date(System.currentTimeMillis());
            bien = new Appartement("addresse1", wilaya2, Transaction.Vente, proprietaire2, d, 120, 4000000, false, "", null, 4, false, 1, false, false);
            immoEsi.ajouterBien(bien);
            bien = new Maison("addresse2", wilaya3, Transaction.Vente, proprietaire1, d, 200, 10000000, false, "", null, 0,  false, 1, true, false, false, 100);
            immoEsi.ajouterBien(bien);
            bien = new Terrain("addresse3", wilaya1, Transaction.Vente, proprietaire1, d, 500, 20000000, false, "", null, 3, "");
            immoEsi.ajouterBien(bien);
            bien = new Appartement("addresse4", wilaya3, Transaction.Location, proprietaire2, d, 100, 40000, false, "", null, 3, false, 1, false, false);
            immoEsi.ajouterBien(bien);
            bien = new Maison("addresse5", wilaya2, Transaction.Location, proprietaire3, d, 160, 150000, false, "", null, 0,  false, 1, false, false, true, 100);
            immoEsi.ajouterBien(bien);
            bien = new Appartement("addresse6", wilaya3, Transaction.Location, proprietaire2, d, 50, 600000, false, "", null, 1, false, 6, false, false);
            immoEsi.ajouterBien(bien);
            bien = new Terrain("addresse7", wilaya1, Transaction.Echange, proprietaire1, d, 650, 18000000, false, "", null, 1, "", wilaya3);
            immoEsi.ajouterBien(bien);
            bien = new Maison("addresse8", wilaya2, Transaction.Echange, proprietaire2, d, 200, 14000000, false, "", null, 0,  false, 1, false, true, false, 100);
            immoEsi.ajouterBien(bien);



        } catch (DuplexException duplexException) {
            System.err.println("Le studio ne peut pas etre un duplex");
        } catch (SuperficieException superficieException) {
            System.err.println("La superficie habitable est plus grande que la superficie non-habitable");
        }

    }
}
