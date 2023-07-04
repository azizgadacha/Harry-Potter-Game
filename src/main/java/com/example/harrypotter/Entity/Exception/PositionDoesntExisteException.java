package com.example.harrypotter.Entity.Exception;

public class PositionDoesntExisteException extends Exception {    //Exception If All house are full
    //Exception If user use a none excetion potion

    public PositionDoesntExisteException() {
        super("Sory you dont have this potion");
    }
}
