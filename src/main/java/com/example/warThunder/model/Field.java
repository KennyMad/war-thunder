package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Field extends AbstractEntity{

    @OneToMany(mappedBy = "field")
    private List<Cell> cells;

    @ManyToOne
    @JoinColumn(name = "game_fk")
    private Game game;

    private long ownerId;
}
