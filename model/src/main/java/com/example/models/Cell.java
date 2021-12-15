package com.example.models;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cell extends AbstractEntity {

    private char x;
    private char y;

    private boolean shot = false;

    @ManyToOne
    @JoinColumn(name = "field_fk")
    private Field field;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ship_fk")
    private Ship ship;

}
