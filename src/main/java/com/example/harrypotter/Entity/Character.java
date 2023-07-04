package com.example.harrypotter.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Character {
    private String name;
    private int XP;
    private int damage;

     public abstract void attack(Character character);
}
