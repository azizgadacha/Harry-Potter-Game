package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enumaration.Core;
import com.example.harrypotter.Entity.Wand;
import com.example.harrypotter.Utils.WizardPlayer;
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

public class CoreChoise {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button Dragonbutton;

    @FXML
    private Button owl;

    @FXML
    private Button phoenex;

Wand wand=new Wand(30);
double x,y;

public  void CreateWand(Core C) throws IOException {
    //creating wand
    wand.setCore(C);
    System.out.println("dd "+wand.getCore());
    WizardPlayer.getWizard().setWand(wand);
//loading  next page

    Stage primaryStage=(Stage) phoenex.getScene().getWindow();

    Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/ChoiseAnimal.fxml"));
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
}
//chose dragon wand
    @FXML
    void ChoiseDragon(ActionEvent event) throws IOException {
        System.out.println("DRAGON_HEARTSTRING");
        CreateWand(Core.DRAGON_HEARTSTRING);

    }
//chose owl wand

    @FXML
    void owlClick(ActionEvent event) throws IOException {
        System.out.println("owl");
        CreateWand(Core.OWL);


    }
//chose phonex wand

    @FXML
    void phoenexClick(ActionEvent event) throws IOException {
        System.out.println("PHOENIX_FEATHER");
    CreateWand(Core.PHOENIX_FEATHER);


    }
    //closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) phoenex.getScene().getWindow();
        stage.close();
    }
}
