package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.HouseName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {

private HouseName Name;
private int Capacity;
private int Number_members;


}
