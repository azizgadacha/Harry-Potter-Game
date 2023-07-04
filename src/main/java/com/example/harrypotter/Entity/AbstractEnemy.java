package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.Place_Name;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class  AbstractEnemy extends  Character{

  private Place_Name location;

  public AbstractEnemy(String name, int XP, int damage, Place_Name location) {
    super(name, XP,damage);
    this.location = location;
  }



}
