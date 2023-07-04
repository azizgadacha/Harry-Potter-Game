package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enemy;
import com.example.harrypotter.Entity.Enumaration.HouseName;
import com.example.harrypotter.Entity.Enumaration.Place_Name;
import com.example.harrypotter.Entity.Level;
import com.example.harrypotter.Utils.WizardPlayer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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

public class Level6Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
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
    private Label ChoiseLabel;
    @FXML
    private ImageView imageCick;
    @FXML
    private ImageView EnemyCick;
    @FXML
    private Button joinButton;
    private Level level6;
    @FXML
    private Button attackButton;
    @FXML
    private Button CombatButton;

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

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/Level7.fxml"));
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
        //inizialize enemis button and progress bar
        imageCick.setVisible(false);
        EnemyCick.setVisible(false);
        Continue.setVisible(false);
        labelWin.setVisible(false);
            getPrograssChar().setProgress((WizardPlayer.getWizard().getXP()) / WizardPlayer.getInizialXP());

        Enemy Death_Eaters = new Enemy("Death_Eaters", 80, 20, Place_Name.ASTRONOMY_TOWER);
        setLevel6(new Level(Death_Eaters, Place_Name.ASTRONOMY_TOWER, WizardPlayer.getWizard()));

        enemyXP=Death_Eaters.getXP();
        Continue.setVisible(false);
        labelWin.setVisible(false);

        if (this.getLevel6().getPlayer().getHouse().getName() == HouseName.SLYTHERIN) {
            //give them the hand to attack enemy  or make aliance and ensure that he choose the right option
            ChoiseLabel.setVisible(true);
            joinButton.setVisible(true);
            CombatButton.setVisible(true);
            attackButton.setVisible(false);

            //he chose to make aliance
            } else{
            //he is not from SLYTHERIN, so he only has the right to attack
        ChoiseLabel.setVisible(false);
        joinButton.setVisible(false);
        CombatButton.setVisible(false);
        attackButton.setVisible(true);

    }}
//chosie to join enemy
    public void join(){
        ChoiseLabel.setVisible(false);
        joinButton.setVisible(false);
        CombatButton.setVisible(false);
        Continue.setVisible(true);
        labelWin.setVisible(true);

    }
    //choseto combat enemy
public void combat(){
    ChoiseLabel.setVisible(false);
    joinButton.setVisible(false);
    CombatButton.setVisible(false);
    attackButton.setVisible(true);
label.setText("You chose to attack them let's do it");
}

//choise to aattack enemy
    public void attack(){


            // attack enemy with  weapon
            //declare the methode of attack Random is function who give you randomly true or false
            Random random = new Random();
            boolean work =random.nextBoolean();
            if (work){
//attack succed
                label.setText("well done you attack the enemy ");
                this.getLevel6().getPlayer().attack(this.getLevel6().getEnemy());
                progressEnemy.setProgress((getLevel6().getEnemy().getXP()) / enemyXP);
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
                if (this.getLevel6().getEnemy().getXP() <= 0) {
                    //enemy die
           this.setLevel_Succed(true);
           Continue.setVisible(true);
           labelWin.setVisible(true);
           attackButton.setVisible(false);
           this.setLevel_Succed(true);

       }}
            else{
                //attack fail
                level6.getEnemy().attack(level6.getPlayer());
                getPrograssChar().setProgress((getLevel6().getPlayer().getXP()) / WizardPlayer.getInizialXP());
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


                if (getLevel6().getPlayer().getXP() <= 0) {
                  //you day
                    attackButton.setVisible(false);
                    labelWin.setVisible(true);
                    labelWin.setText("Sorry You Lost");
                }else
                label.setText("you missed the shot the enemy attacked you");

            }


        }
//closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) progressEnemy.getScene().getWindow();
        stage.close();
    }







}
