package com.example.harrypotter.Controller;

import com.example.harrypotter.Entity.Enemy;
import com.example.harrypotter.Entity.Enumaration.HouseName;
import com.example.harrypotter.Entity.Enumaration.Place_Name;
import com.example.harrypotter.Entity.Exception.AlreadyHaveWeaponException;
import com.example.harrypotter.Entity.Exception.DontHaveWeaponException;
import com.example.harrypotter.Entity.Level;
import com.example.harrypotter.Entity.Spell;
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

public class Level3Controller implements Initializable {
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
    private Button AttackEnemyButton;
    private Level level3;
    @FXML
    private Button UseSort;

    private boolean Level_Succed=false;
    private  boolean Has_weapon=false;
    float enemyXP;
    @FXML
    private AnchorPane anchorPane;
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

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/harrypotter/Level4.fxml"));
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


        Enemy enemy=new Enemy("Dementors",70,50, Place_Name.LAKE_IN_THE_FORBIDDEN_FOREST);
        setLevel3(new Level(enemy, Place_Name.LAKE_IN_THE_FORBIDDEN_FOREST,WizardPlayer.getWizard()));

//if he is from GRYFFINDOR he already has a weapon

        System.out.println(PrograssChar.getProgress());
        enemyXP=enemy.getXP();

        Continue.setVisible(false);
        labelWin.setVisible(false);
        UseSort.setVisible(false);
        learnSortButton.setVisible(true);
    }
    private boolean Spell_Learned=false;
    //learning spell
    public  void Learn_Spell(){
        this.setSpell_Learned(true);
        this.getLevel3().getPlayer().getKnownSpells().add(new Spell("Expectro Patronum","Expectro Patronum","save you"));
        label.setText("well done you learned now the spell used to kill them");
        UseSort.setVisible(true);
        learnSortButton.setVisible(false);
    }


    //attack enemy function if he dont have a weapon an exception will be declared
    public void kill_Enemy(){
        //a random function who give you if spell work or not
        Random random = new Random();
        boolean work =random.nextBoolean();
        if (work){
            //if work he kills enemy

            //level3.getPlayer().attack(level3.getEnemy());
            level3.getEnemy().setXP(0);
            
            progressEnemy.setProgress((getLevel3().getEnemy().getXP()) / enemyXP);
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
            setLevel_Succed(true);
            System.out.println("well done you killed  enemy");
            Continue.setVisible(true);
            labelWin.setVisible(true);
            UseSort.setVisible(false);
        }else {
            //else he should relearn spell
            System.out.println("sorry Spell didn't work learn it again");
            this.setSpell_Learned(false);
            getPrograssChar().setProgress((getLevel3().getPlayer().getXP()) / WizardPlayer.getInizialXP());

            learnSortButton.setVisible(true);
            UseSort.setVisible(false);
            label.setText("sorry Spell didn't work learn it again");

        }
    }

//closing page image code
    @FXML
    void closePage(MouseEvent event) {
        Stage stage = (Stage) progressEnemy.getScene().getWindow();
        stage.close();
    }

}
