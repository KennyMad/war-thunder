package com.example.warThunder.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell extends AbstractEntity {

    private char x;
    private char y;

    @Column
    private boolean shooted = false;

    @ManyToOne
    @JoinColumn(name = "field_fk")
    private Field field;

    @ManyToOne
    @JoinColumn(name = "ship_fk")
    private Ship ship;

}
