package sample;
import java.net.URL ;
import java.util.ResourceBundle ;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent ;
import javafx.util.Duration;

public class Controller  {
    private Label label;
    @FXML
    private Label labelAdmin ;
    @FXML
    private Label labelUser ;
    @FXML
    private Label labelStatus ;
    @FXML
    private TabPane tabPanelogin ;
    @FXML
    private Tab tabUser ;
    @FXML
    private Tab tabAdmin ;
    @FXML
    private Pane SlidingPane ;
    @FXML
    public void handleButtonAction (ActionEvent actionEvent) {
        System.out.println("You clicked me");
        label.setText("Hello word");
    }
     @FXML
    //Transition to admin mode in the login page
    private void openAdminTab(MouseEvent event) {
        //Translation animation to slide labels left and right
        TranslateTransition toLeftAnimation = new TranslateTransition(new Duration(500) , labelStatus) ;
        toLeftAnimation.setToX(SlidingPane.getTranslateX()) ;
        toLeftAnimation.play();
        toLeftAnimation.setOnFinished((ActionEvent event2) -> labelStatus.setText("Admin")) ;
        //set selected tab as admin
        tabPanelogin.getSelectionModel().select(tabAdmin);
    }
    @FXML
    //Transition to user mode in the login page
    private void openUserTab(MouseEvent event) {
        //Translation animation to slide labels left and right
        TranslateTransition toRightAnimation = new TranslateTransition(new Duration(500) , labelStatus) ;
        toRightAnimation.setToX(SlidingPane.getTranslateX()+(SlidingPane.getPrefWidth()-labelStatus.getPrefWidth()));
        toRightAnimation.play();
        toRightAnimation.setOnFinished((ActionEvent event1) -> labelStatus.setText("User")) ;
        //set selected tab as user
        tabPanelogin.getSelectionModel().select(tabUser);
    }
}
