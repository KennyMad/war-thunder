package com.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Field extends AbstractEntity {

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Cell> cells;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "game_fk")
    private Game game;

    private Long ownerId;
}
