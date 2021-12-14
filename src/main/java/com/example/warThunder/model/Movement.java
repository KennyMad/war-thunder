package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movement extends AbstractEntity {

    private char x;
    private char y;

    private Long userId;

    private int turnNumber;

    @ManyToOne
    @JoinColumn(name = "history_fk")
    private History history;
}
