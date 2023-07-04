package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enumaration.Core;
import com.example.harrypotter.Entity.Enumaration.Pet;
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

public class PetChoise {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button CatButton;
double x,y;

public  void CreateAnimal(Pet pet) throws IOException {

    WizardPlayer.getWizard().setPet(pet);
    //loading  next page

    Stage primaryStage=(Stage) CatButton.getScene().getWindow();

    Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/ChoiseHouse.fxml"));
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
//choise cat as animal
    @FXML
    void ChoiseCat(ActionEvent event) throws IOException {
        CreateAnimal(Pet.CAT);
    }
//choise toad as animal
    @FXML
    void ChoiseToad(ActionEvent event) throws IOException {
        CreateAnimal(Pet.TOAD);

    }
//choise rat as animal

    @FXML
    void ClickRat(ActionEvent event) throws IOException {
        CreateAnimal(Pet.RAT);

    }
//choise owl as animal

    @FXML
    void OwlClicj(ActionEvent event) throws IOException {
        CreateAnimal(Pet.OWL);

    }
    //closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) CatButton.getScene().getWindow();
        stage.close();
    }

}
