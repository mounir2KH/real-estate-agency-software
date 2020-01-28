package poo_graphic;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import poo_graphic.Models.Agence;

import java.io.IOException;

public class Controller {
    private Label label;
    @FXML
    private Label labelAdmin;
    @FXML
    private Label labelUser;
    @FXML
    private Label labelStatus;
    @FXML
    private TabPane tabPanelogin;
    @FXML
    private Tab tabUser;
    @FXML
    private Tab tabAdmin;
    @FXML
    private Pane SlidingPane;
    @FXML
    private Button login;
    @FXML
    private PasswordField passwordField;


    private Agence immoEsi;


    public void handleButtonAction(ActionEvent actionEvent) {
        System.out.println("You clicked me");
        label.setText("Hello word");
    }

    @FXML
    //Transition to admin mode in the login page
    private void openAdminTab(MouseEvent event) {
        //Translation animation to slide labels left and right
        TranslateTransition toLeftAnimation = new TranslateTransition(new Duration(500), labelStatus);
        toLeftAnimation.setToX(SlidingPane.getTranslateX());
        toLeftAnimation.play();
        toLeftAnimation.setOnFinished((ActionEvent event2) -> labelStatus.setText("Admin"));
        //set selected tab as admin
        tabPanelogin.getSelectionModel().select(tabAdmin);
    }

    @FXML
    //Transition to user mode in the login page
    private void openUserTab(MouseEvent event) {
        //Translation animation to slide labels left and right
        TranslateTransition toRightAnimation = new TranslateTransition(new Duration(500), labelStatus);
        toRightAnimation.setToX(SlidingPane.getTranslateX() + (SlidingPane.getPrefWidth() - labelStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1) -> labelStatus.setText("User"));
        //set selected tab as user
        tabPanelogin.getSelectionModel().select(tabUser);
    }

    @FXML
    //login
    private void loginAsUser(MouseEvent event) throws Exception {
        Stage stage = (Stage) login.getScene().getWindow();
        Parent root;
        stage.setTitle("User");
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("userPage.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            UserPageController controller = loader.getController();
            controller.setImmoEsi(immoEsi);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    //login
    private void loginAsAdmin(MouseEvent event) throws Exception {
        Stage stage = (Stage) login.getScene().getWindow();
        Parent root;
        stage.setTitle("Admin");
        String password = passwordField.getText();
        if (immoEsi.login(password)) {
            try {
                // Load root layout from fxml file.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("AdminPage.fxml"));
                AnchorPane rootLayout = (AnchorPane) loader.load();
                AdminPageController controller = loader.getController();
                controller.setImmoEsi(immoEsi);

                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //TODO : show error message
        }
    }

    public void setImmoEsi(Agence immoEsi) {
        this.immoEsi = immoEsi;
    }
}
