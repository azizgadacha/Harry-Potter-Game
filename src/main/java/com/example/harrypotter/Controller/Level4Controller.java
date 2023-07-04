package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.*;
import com.example.harrypotter.Entity.Enumaration.Core;
import com.example.harrypotter.Entity.Enumaration.Place_Name;
import com.example.harrypotter.Utils.WizardPlayer;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

@Data

public class Level4Controller implements Initializable {
    @FXML
    private ProgressBar PrograssChar;
    @FXML
    private Button Continue;
    @FXML
    private Button learnSortButton;
    @FXML
    private ProgressBar progressEnemy;
    @FXML
    private Button ThrowButton;
    @FXML
    private Label label;
    @FXML
    private Button runawayButton;
    private Level level4;
    @FXML
    private Button UseSort;

    private boolean Level_Succed=false;
    private  boolean Has_weapon=false;
    float enemyXP;
    @FXML
    private Label labelWin;
    @FXML

    private AnchorPane anchorPane;
double x,y;
//confirm moving and moving to next level

    public void confirm() throws IOException {
        //loading  next page

        Stage primaryStage=(Stage) labelWin.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/Level5.fxml"));
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
        primaryStage.show();}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //inizialing enemy button and progresss

            getPrograssChar().setProgress((WizardPlayer.getWizard().getXP()) / WizardPlayer.getInizialXP());

        Enemy Peter=new Enemy("Peter Pettigrow",30,20, Place_Name.LITTLE_HANGLETON_CEMETERY);
        Boss Voldemort=new Boss("Voldemort",160,20, Place_Name.LITTLE_HANGLETON_CEMETERY,Peter,new Wand(20, Core.OWL));
        setLevel4(new Level(Voldemort, Place_Name.LITTLE_HANGLETON_CEMETERY,WizardPlayer.getWizard()));


        enemyXP=Voldemort.getXP();
        Continue.setVisible(false);
        labelWin.setVisible(false);

    }



    public void runaway(){

            setLevel_Succed(true);
            System.out.println("well done you killed  enemy");
            Continue.setVisible(true);
            labelWin.setText("Well Done you escaped Continue ");
            labelWin.setVisible(true);

    }
//closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) progressEnemy.getScene().getWindow();
        stage.close();
    }


}
