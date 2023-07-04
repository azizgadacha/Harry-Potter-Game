package com.example.harrypotter.Entity;

import com.example.harrypotter.Entity.Enumaration.Core;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wand {
    private int size;
    private Core core;

    public Wand(int size) {
        this.size=size;
    }
}
