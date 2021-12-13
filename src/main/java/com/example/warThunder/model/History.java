package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History extends AbstractEntity{

    @OneToOne
    private Game game;

    @ManyToMany
    private List<User> users;

    @OneToMany
    private List<Movement> movements;
}
