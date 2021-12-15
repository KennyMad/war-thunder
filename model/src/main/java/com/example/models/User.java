package com.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {

    private String name;
    private String password;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games;

}
