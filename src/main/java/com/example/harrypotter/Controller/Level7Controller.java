package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Boss;
import com.example.harrypotter.Entity.Enemy;
import com.example.harrypotter.Entity.Enumaration.Core;
import com.example.harrypotter.Entity.Enumaration.Place_Name;
import com.example.harrypotter.Entity.Level;
import com.example.harrypotter.Entity.Wand;
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

@Data

public class Level7Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button attackbutoon;
    @FXML
    private Label labelLose;
    @FXML
    private ImageView image;
    @FXML
    private ProgressBar PrograssChar;
    @FXML
    private Button Continue;
    @FXML
    private Button learnSortButton;
    @FXML
    private ProgressBar progressEnemy;
    @FXML
    private Button getFireBoxButton1;
    @FXML
    private Label label;
    @FXML
    private ImageView imageCick;
    @FXML
    private ImageView EnemyCick;
    @FXML
    private Button runawayButton;
    private Level level7;
    @FXML
    private Button attackButton;

    private boolean Level_Succed=false;
    private  boolean Has_weapon=false;
    float enemyXP;
    @FXML
    private Label labelWin;

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
        imageCick.setVisible(false);
        EnemyCick.setVisible(false);
        Enemy Bellatrix_Lestrange=new Enemy("Bellatrix Lestrange",10,20, Place_Name.LITTLE_HANGLETON_CEMETERY);
        Boss Voldemort=new Boss("Voldemort",30,20, Place_Name.LITTLE_HANGLETON_CEMETERY,Bellatrix_Lestrange,new Wand(50, Core.DRAGON_HEARTSTRING));
        setLevel7(new Level(Voldemort, Place_Name.HOGWARTS,WizardPlayer.getWizard()));
            getPrograssChar().setProgress((WizardPlayer.getWizard().getXP()) / WizardPlayer.getInizialXP());


        enemyXP=Voldemort.getXP();
        Continue.setVisible(false);
        labelWin.setVisible(false);

        attackbutoon.setVisible(true);
        labelLose.setVisible(false);
    }




//closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) progressEnemy.getScene().getWindow();
        stage.close();
    }
    @FXML
    void attack(ActionEvent event) {
        //attack if   they have same wand both are die

        if (((Boss)this.getLevel7().getEnemy()).getWand().getCore()==this.getLevel7().getPlayer().getWand().getCore()) {
            level7.getPlayer().setXP(0);
           label.setText("You both have the same bread core, something unpredictable is happening  ");
            labelLose.setVisible(true);
            Continue.setVisible(true);
            labelWin.setVisible(true);
        }
        else{
            // attack enemy with  weapon
            //declare the methode of attack Random is function who give you randomly true or false
            Random random = new Random();
            boolean work =random.nextBoolean();
            if (work) {
                //attack succes
                level7.getPlayer().attack(((Boss) this.getLevel7().getEnemy()));
                label.setText("well done you attack the enemy ");
                progressEnemy.setProgress((getLevel7().getEnemy().getXP()) / enemyXP);
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
                //animal die
                if (this.getLevel7().getEnemy().getXP() <= 0) {
                    this.setLevel_Succed(true);
                    labelWin.setVisible(true);
                    labelWin.setText("You win the game");
                    attackbutoon.setVisible(false);
                    this.setLevel_Succed(true);

            }}
            else{
                //attack field
                level7.getEnemy().attack(level7.getPlayer());
                getPrograssChar().setProgress((getLevel7().getPlayer().getXP()) / WizardPlayer.getInizialXP());
                imageCick.setVisible(true);

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
                if (getLevel7().getPlayer().getXP() <= 0) {
                    attackButton.setVisible(false);
                    labelWin.setVisible(true);
                    labelWin.setText("Sorry You Lost");
                }else
                    label.setText("you missed the shot the enemy attacked you");

            }
        }


}
}
