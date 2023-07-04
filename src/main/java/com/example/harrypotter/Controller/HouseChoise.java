package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enumaration.HouseName;
import com.example.harrypotter.Entity.Exception.HousesFullException;
import com.example.harrypotter.Entity.House;
import com.example.harrypotter.Entity.SortingHat;
import com.example.harrypotter.Utils.WizardPlayer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HouseChoise implements Initializable {
    @FXML
    private Label NameLabel;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView img;
    double x,y;

    @FXML
    void Confirming(ActionEvent event) throws IOException {
        //loading  next page

        Stage primaryStage=(Stage) img.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/Level1.fxml"));
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
        primaryStage.show();    }
    public ArrayList<House> Inizialize_House_Liste(){
        ArrayList<House> houses=new ArrayList<>();
        houses.add(new House(HouseName.HUFFLEPUFF,10,10));
        houses.add(new House(HouseName.SLYTHERIN,10,8));
        houses.add(new House(HouseName.GRYFFINDOR,10,10));
        houses.add(new House(HouseName.RAVENCLAW,50,50));
        return houses;
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SortingHat sortingHat=new SortingHat();
        try {// assign house to user if are house are full it show exception then redirect him  to Main game class where he will play
            sortingHat.Assign_House(WizardPlayer.getWizard(),Inizialize_House_Liste());
            NameLabel.setText(WizardPlayer.getWizard().getHouse().getName().toString());
            if(WizardPlayer.getWizard().getHouse().getName()==HouseName.GRYFFINDOR){
                Image image=new Image(getClass().getResourceAsStream("/com/example/harrypotter/img/gryffindor.png"));
            img.setImage(image);
            }
            else if(WizardPlayer.getWizard().getHouse().getName()==HouseName.SLYTHERIN){
                Image image=new Image(getClass().getResourceAsStream("/com/example/harrypotter/img/slytherin.png"));
                img.setImage(image);

            }
            else if(WizardPlayer.getWizard().getHouse().getName()==HouseName.RAVENCLAW){
                Image image=new Image(getClass().getResourceAsStream("/com/example/harrypotter/img/ravenclaw.png"));
                img.setImage(image);

            }else {
                Image image=new Image(getClass().getResourceAsStream("/com/example/harrypotter/img/hufflepuff.png"));
                img.setImage(image);

            }

    } catch (HousesFullException e) {
            System.out.println((e.getMessage()));}
    }
//closing page image code

    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) img.getScene().getWindow();
        stage.close();
    }
}


