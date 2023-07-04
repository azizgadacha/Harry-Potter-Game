package com.example.harrypotter.Controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button StartButton;
    double x,y;
    @FXML
    private AnchorPane anchorPane;
    @FXML
void  StartClick() throws IOException {

//loading  next page

        Stage primaryStage=(Stage) StartButton.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/ChoiseName.fxml"));
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
        //animation betwenn scenes


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


/*    FadeTransition fadeTransition=new FadeTransition();
        anchorPane.setOpacity(0.0);

    fadeTransition.setDuration(Duration.millis(300));
    fadeTransition.setNode(anchorPane);
        fadeTransition.setNode(root);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    ;
    fadeTransition.setOnFinished((ActionEvent event)->{
            anchorPane.setStyle("-fx-background-color: black;");

    });

    fadeTransition.play();*/
}



//closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) StartButton.getScene().getWindow();
        stage.close();
    }
}
