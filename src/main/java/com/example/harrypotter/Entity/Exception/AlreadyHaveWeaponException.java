package com.example.harrypotter.Entity.Exception;

public class AlreadyHaveWeaponException extends Exception{
    //Exception If user already have a weapon this can help in  javafx
    public AlreadyHaveWeaponException() {
        super("you already  have a weapon");
    }
}
