package com.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game extends AbstractEntity {

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Field> fields;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private History history;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> users;

}
