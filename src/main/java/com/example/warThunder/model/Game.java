package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game extends AbstractEntity{

    @OneToMany
    private List<Field> fields;

    @OneToOne
    private History history;

    @ManyToMany
    private List<User> users;

}
