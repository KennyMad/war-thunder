package com.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History extends AbstractEntity {

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL)
    private List<Movement> movements;
}
