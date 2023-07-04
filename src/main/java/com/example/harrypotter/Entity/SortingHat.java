package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Exception.HousesFullException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortingHat {
    private String name;

    public  int getRandomIntInRange(int max) {
        Random random = new Random();
        return random.nextInt(max + 1) ;
    }

    //methode used to assign house to user
    public void Assign_House(Wizard wizard,List<House> houses) throws HousesFullException {
        int i=0;
        ArrayList<House> Free_House=new ArrayList<>();
        //get houses who can accept more user

        while ((i<houses.size())){
            if(houses.get(i).getCapacity()>houses.get(i).getNumber_members()){
            Free_House.add(houses.get(i));}
            i++;
        }


        if (Free_House.size()>0) {
//if there is free houses it assigns one of them randomly  to the user
             int Chosen_House=getRandomIntInRange((Free_House.size()-1));
             int Index_In_House=houses.indexOf(Free_House.get(Chosen_House));
             House house=houses.get(Index_In_House);
             house.setNumber_members((house.getNumber_members())+1);
             wizard.setHouse(house);
           }else {
            //if there is not an exception is triggered to inform user that there is not free house

            throw new HousesFullException();
           }
    }

}
