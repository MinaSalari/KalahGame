package com.minasalari.kalah.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Pit {
    private int stoneNumber;
    private Player owner;
    private PitType pitType;

}
