package com.example.warThunder.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity{

    private String name;
    private String password;

    @ManyToMany
    private List<History> gameHistory;

    @ManyToMany
    private List<Game> games;

}
