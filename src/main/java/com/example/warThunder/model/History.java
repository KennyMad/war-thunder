package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class History extends AbstractEntity{

    @OneToOne
    private Field field;

    @ManyToMany
    private List<User> users;

    @OneToMany
    private List<Movement> movements;
}