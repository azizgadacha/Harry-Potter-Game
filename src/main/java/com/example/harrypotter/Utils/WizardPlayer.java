package com.example.harrypotter.Utils;

import com.example.harrypotter.Entity.Potion;
import com.example.harrypotter.Entity.Spell;
import com.example.harrypotter.Entity.Wizard;

import java.util.ArrayList;


public class WizardPlayer {
    static Wizard wizard=new Wizard("",200,10, null,null,null,new ArrayList<Spell>(),new ArrayList<Potion>());
    public static Wizard getWizard() {


        return wizard;
    }

    static float inizialXp=wizard.getXP();
public static float getInizialXP(){
    return inizialXp;
}

    public static void setWizard(Wizard wizard) {

        inizialXp=wizard.getXP();
        WizardPlayer.wizard = wizard;
    }
}
