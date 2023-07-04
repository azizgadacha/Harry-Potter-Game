package com.example.harrypotter.Controller;

import com.example.harrypotter.Utils.WizardPlayer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoiseName implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button Confirm;

    @FXML
    private Label InvalidLabel;
    @FXML
    private TextField input;
    //ckeck if name is valid
    @FXML
    void CheckName(KeyEvent event) {
        InvalidLabel.setVisible(input.getText().length() <= 4);
    }
double x,y;
    //connfirm the name
    @FXML
    void confirm(ActionEvent event) throws IOException {
if (input.getText().length() > 4){
    System.out.println("heello");

    WizardPlayer.getWizard().setName(input.getText());
    System.out.println(WizardPlayer.getWizard().getName());
    //loading  next page

    Stage primaryStage=(Stage) Confirm.getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/ChoiseWand.fxml"));
    primaryStage.setScene(new Scene(root));
    //set stage borderless

    //drag it here
    root.setOnMousePressed(event1 -> {
        x = event1.getSceneX();
        y = event1.getSceneY();
    });
    root.setOnMouseDragged(event1 -> {

        primaryStage.setX(event1.getScreenX() - x);
        primaryStage.setY(event1.getScreenY() - y);

    });
 //   transition  btwenn scenes
    FadeTransition fade = new FadeTransition(new Duration(10), anchorPane);
    fade.setFromValue(0);
    fade.setToValue(1);
    fade.play();
    FadeTransition fade2 = new FadeTransition(new Duration(550), root);
    fade2.setFromValue(0);
    fade2.setToValue(1);
    root.setOpacity(0);
    fade2.play();
    primaryStage.show();
}else InvalidLabel.setVisible(true);
    }
//inizalizing Interface   and set Display Error message off
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InvalidLabel.setVisible(false);

    }
    //closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) input.getScene().getWindow();
        stage.close();
    }
}
