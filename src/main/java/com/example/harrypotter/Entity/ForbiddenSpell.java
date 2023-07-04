package com.example.harrypotter.Entity;

import lombok.Data;

@Data

public class ForbiddenSpell extends AbsreactSpell {
         private  String side_efeect;

    public ForbiddenSpell(String name, String description, String side_efeect) {
        super(name, description);
        this.side_efeect = side_efeect;
    }
}
