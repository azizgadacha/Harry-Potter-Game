package com.example.harrypotter.Entity.Exception;

public class DontHaveWeaponException extends  Exception{
    //Exception If user want to attack enemy without weapon  this can help in  javafx

    public DontHaveWeaponException() {
        System.out.println("you don't have a weapon");
    }
}