package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.HouseName;
import com.example.harrypotter.Entity.Enumaration.Pet;
import com.example.harrypotter.Entity.Exception.PositionDoesntExisteException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Wizard extends Character {
    private Pet pet;
    private Wand wand;
    private House house;
    private List<Spell>knownSpells=new ArrayList<>();
    private List<Potion> potions=new ArrayList<>();
    public Wizard(){}
    public Wizard(String name, int XP,int damage, Pet pet,Wand wand, House house, List<Spell> knownSpells, List<Potion> potions) {
        super(name, XP,damage);
        this.pet = pet;
        this.wand = wand;
        this.house = house;
        this.knownSpells = knownSpells;
        this.potions = potions;
    }

    public void UsePotion(Potion potion) throws PositionDoesntExisteException {
        //verify if position excite

        if(potions.contains(potion)){
            //if he is from HUFFLEPUFF an additional Xp will be added to potion added Xp else only the Xp added with the potion  will be added to user

            if (this.getHouse().getName()== HouseName.HUFFLEPUFF)
                this.setXP((this.getXP()+potion.getPoint_Add()+10));
            else
                this.setXP((this.getXP()+potion.getPoint_Add()));
            potions. remove(potion);}
        else
            throw  new PositionDoesntExisteException();
    }
    @Override
    //atack enemy if he is from RAVENCLAW an extra damage point will be added
    public void attack(Character character) {
        if(this.getHouse().getName()==HouseName.RAVENCLAW)
            this.setDamage(this.getDamage()+20);
        character.setXP(character.getXP()-this.getDamage());
    }
    //if an enemy attack him and he defend him self he will get Xp back
    public void defend(Character character) {
        int returnXP=(character.getDamage()-10);
        if(this.getHouse().getName()== HouseName.GRYFFINDOR)
            returnXP=returnXP+10;
        this.setXP(this.getXP()+returnXP);
    }
}
