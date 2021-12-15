package com.example.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship extends AbstractEntity {

    @ToString.Exclude
    @OneToMany(mappedBy = "ship")
    private List<Cell> cells;

    private Integer size;

}
