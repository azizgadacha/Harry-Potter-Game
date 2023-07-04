package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.Place_Name;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Level {
    private  AbstractEnemy Enemy;
    private Place_Name location;
    private  Wizard player;

}
