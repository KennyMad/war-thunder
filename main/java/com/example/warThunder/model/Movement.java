package com.example.warThunder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movement extends AbstractEntity{

    private char x;
    private char y;

    private Long userId;

    private int turnNumber;

    @ManyToOne
    private History history;
}
