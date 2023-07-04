package com.example.harrypotter.Entity;


import lombok.Data;

@Data
public class Spell extends AbsreactSpell{
     private String  good_effect;

     public Spell(String name, String description, String good_effect) {
          super(name, description);
          this.good_effect = good_effect;
     }

}
