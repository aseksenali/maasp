package org.kbtu.maaspservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "point")
    private int point;
    @Column(name = "item")
    private String item;
    @Column(name = "cases")
    private String cases;
}
