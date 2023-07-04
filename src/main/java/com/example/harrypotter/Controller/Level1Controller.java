package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enemy;
import com.example.harrypotter.Entity.Enumaration.Place_Name;
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
import javafx.scene.image.Image;
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

public class Level1Controller implements Initializable {

    @FXML
    private ProgressBar PrograssChar;
    @FXML
    private Button Continue;
    @FXML
    private ImageView imageCick;
    @FXML
    private ImageView EnemyCick;
    @FXML
    private ProgressBar progressEnemy;
    @FXML
    private Button ThrowButton;
    @FXML
    private Label label;
    private Level level1;
    private boolean Level_Succed=false;
    float enemyXP;
    float playerXp;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelWin;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //inizializing enemy and set button display
        imageCick.setVisible(false);
        EnemyCick.setVisible(false);
        Enemy enemy=new Enemy("Troll",30,50, Place_Name.TOILETS_OF_DUNGEON);
        System.out.println(PrograssChar.getProgress());
        enemyXP=enemy.getXP();
        playerXp=WizardPlayer.getWizard().getXP();
        setLevel1(new Level(enemy,Place_Name.TOILETS_OF_DUNGEON, WizardPlayer.getWizard()));
        Continue.setVisible(false);
     /*   Image img=new Image(getClass().getResourceAsStream("/com/example/harrypotter/img/llll.jpg"));

        ImageView view = new ImageView(img);
        view.setPreserveRatio(true);

        label.setGraphic(view);*/
        labelWin.setVisible(false);
    }
double x,y;
//confirm moving and moving to next level
    public void confirm() throws IOException {
        //loading  next page

        Stage primaryStage=(Stage) labelWin.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/Level2.fxml"));
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
        primaryStage.show(); }

    @FXML
    void ThrowStone(ActionEvent event) throws InterruptedException {
        Random random = new Random();
        imageCick.setVisible(true);
        boolean work =random.nextBoolean();
        if (work) {
            {
                //attack animation

                getLevel1().getPlayer().attack(getLevel1().getEnemy());
                progressEnemy.setProgress((getLevel1().getEnemy().getXP())/enemyXP);
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
                if(getLevel1().getEnemy().getXP()<=0) {
                    this.setLevel_Succed(true);
                    ThrowButton.setVisible(false);
                    Continue.setVisible(true);
                    labelWin.setVisible(true);
                }
            }
            label.setText("good throw an other time");
        }
        else{



            level1.getEnemy().attack(level1.getPlayer());
            getPrograssChar().setProgress((getLevel1().getPlayer().getXP())/playerXp);
            TranslateTransition transition=new TranslateTransition();
            transition.setNode(imageCick);
            transition.setDuration(Duration.millis(900));
            transition.setByX(100);
            transition.play();
            transition.setOnFinished((event1 ->  {
                imageCick.setVisible(false);
                // imageCick.setLayoutX(100);
                //attack animation

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
            if(getLevel1().getPlayer().getXP()<=0){
            ThrowButton.setVisible(false);
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
