package com.example.harrypotter.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
abstract class AbsreactSpell {
    private String name;
    private String description ;

}
