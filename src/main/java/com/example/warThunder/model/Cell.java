package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell extends AbstractEntity {

    private char x;
    private char y;

    @Column(columnDefinition = "boolean default false")
    private boolean shooted;

    @ManyToOne
    private Field field;

    @ManyToOne
    private Ship ship;

}
