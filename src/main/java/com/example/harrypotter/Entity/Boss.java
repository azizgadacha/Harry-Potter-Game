package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.Place_Name;
import lombok.Data;

@Data
public class Boss extends  AbstractEnemy{
    private  Enemy Animal_Controlled;
    private  Wand wand;

    public Boss(String name, int XP, int damage, Place_Name location, Enemy animal_Controlled, Wand wand) {
        super(name, XP, damage, location);
        Animal_Controlled = animal_Controlled;
        this.wand=wand;
    }

    public void attack(Character character) {
        character.setXP(character.getXP()-this.getDamage());
    }

}
