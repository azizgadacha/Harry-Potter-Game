package com.example.harrypotter.Entity.Exception;

public class HousesFullException extends Exception{
    //Exception If All house are full

    public HousesFullException() {
        super("Sory all house are full");
    }
}
