package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enemy;
import com.example.harrypotter.Entity.Enumaration.HouseName;
import com.example.harrypotter.Entity.Enumaration.Place_Name;
import com.example.harrypotter.Entity.Exception.AlreadyHaveWeaponException;
import com.example.harrypotter.Entity.Exception.DontHaveWeaponException;
import com.example.harrypotter.Entity.Level;
import com.example.harrypotter.Utils.WizardPlayer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

@Data

public class Level2Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ProgressBar PrograssChar;
    @FXML
    private Button Continue;
    @FXML
    private ProgressBar progressEnemy;
    @FXML
    private Button ThrowButton;
    @FXML
    private Label label;
    @FXML
    private Button AttackEnemyButton;
    private Level level2;
    @FXML
    private Button getWeapon;
    private boolean Level_Succed=false;
    private  boolean Has_weapon=false;
    float enemyXP;
    @FXML
    private Label labelWin;
    @FXML
    private ImageView imageCick;
    @FXML
    private ImageView EnemyCick;
double x,y;
//confirm moving and moving to next level

    public void confirm() throws IOException {
//loading  next page

        Stage primaryStage=(Stage) labelWin.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/Level3.fxml"));
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

        imageCick.setVisible(false);
        EnemyCick.setVisible(false);
        Continue.setVisible(false);
        labelWin.setVisible(false);
        try {
            getPrograssChar().setProgress((WizardPlayer.getWizard().getXP()) / WizardPlayer.getInizialXP());
        }catch (Exception e){
            System.out.println(e);
        }
        Enemy enemy=new Enemy("Basilisk",30,20, Place_Name.ROOM_OF_SECRETS);

        this.setLevel2(new Level(enemy,Place_Name.ROOM_OF_SECRETS,WizardPlayer.getWizard()));
//if he is from GRYFFINDOR he already has a weapon
        if (WizardPlayer.getWizard().getHouse().getName()== HouseName.GRYFFINDOR){

            this.Has_weapon=true;
            label.setText("Attack enemy");
        }else
            label.setText("Get weapon from enemy");
        System.out.println(PrograssChar.getProgress());
        enemyXP=enemy.getXP();


        if (Has_weapon) {
            getWeapon.setVisible(false);
        }else
            AttackEnemyButton.setVisible(false);
    }
    public   void getWeapon() throws AlreadyHaveWeaponException {
//if he dont have a weapon he  will get  it else an exception will be declared
        if (!Has_weapon){
            Has_weapon=true;
            getWeapon.setVisible(false);
            AttackEnemyButton.setVisible(true);
        }
        else
            throw  new AlreadyHaveWeaponException();}


    //attack enemy function if he dont have a weapon an exception will be declared
    public  void AttackEnemy( ) throws DontHaveWeaponException {

        // attack enemy with  weapon
        //declare the methode of attack Random is function who give you randomly true or false
        Random random = new Random();
        boolean work = random.nextBoolean();
        if (work) {



            level2.getPlayer().attack(level2.getEnemy());
            getLevel2().getPlayer().attack(getLevel2().getEnemy());
            progressEnemy.setProgress((getLevel2().getEnemy().getXP()) / enemyXP);
            //attack animation

            imageCick.setVisible(true);
            TranslateTransition transition=new TranslateTransition();
            transition.setNode(imageCick);
            transition.setDuration(Duration.millis(900));
            transition.setByX(250);

            transition.play();

            transition.setOnFinished((event1 ->  {
                imageCick.setVisible(false);
                TranslateTransition transition2=new TranslateTransition();
                transition2.setNode(imageCick);
                transition2.setDuration(Duration.millis(10));
                transition2.setByX(-250);
                transition2.play();



            }));
            if (getLevel2().getEnemy().getXP() <= 0) {
                this.setLevel_Succed(true);
                AttackEnemyButton.setVisible(false);
                Continue.setVisible(true);
                labelWin.setVisible(true);
            } else
                label.setText("Good shot attack an other time");


        } else {
            level2.getEnemy().attack(level2.getPlayer());
            imageCick.setVisible(true);
            //attack animation

            TranslateTransition transition=new TranslateTransition();
            transition.setNode(imageCick);
            transition.setDuration(Duration.millis(900));
            transition.setByX(100);
            transition.play();
            transition.setOnFinished((event1 ->  {
                imageCick.setVisible(false);
                // imageCick.setLayoutX(100);
                TranslateTransition transition2=new TranslateTransition();
                transition2.setNode(imageCick);
                transition2.setDuration(Duration.millis(10));
                transition2.setByX(-100);
                transition2.play();
                EnemyCick.setVisible(true);
                TranslateTransition transition3=new TranslateTransition();
                transition3.setNode(EnemyCick);
                transition3.setDuration(Duration.millis(900));
                transition3.setByX(-250);
                transition3.play();
                transition3.setOnFinished((event2 -> {
                    EnemyCick.setVisible(false);
                    TranslateTransition transition4=new TranslateTransition();
                    transition4.setNode(EnemyCick);
                    transition4.setDuration(Duration.millis(10));
                    transition4.setByX(250);
                    transition4.play();
                }));
            }));
            label.setText("you missed the shot the enemy attacked you");
            getPrograssChar().setProgress((getLevel2().getPlayer().getXP()) / WizardPlayer.getInizialXP());

            if (getLevel2().getPlayer().getXP() <= 0) {
                AttackEnemyButton.setVisible(false);
                labelWin.setVisible(true);
                labelWin.setText("Sorry You Lost");
            }
        }

    }
//closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) progressEnemy.getScene().getWindow();
        stage.close();
    }


}
