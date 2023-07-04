package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.Place_Name;


public class Enemy extends AbstractEnemy {

    public Enemy(String name, int XP, int damage, Place_Name location) {
        super(name, XP, damage, location);
    }
    public void attack(Character character) {
        character.setXP(character.getXP()-this.getDamage());
    }

}
