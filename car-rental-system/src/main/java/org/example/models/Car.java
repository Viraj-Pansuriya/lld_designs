package org.example.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends Vehicle {
    private boolean hasSunRoof;
    private int numberOfSheets;

}
