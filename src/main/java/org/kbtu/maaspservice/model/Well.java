package org.kbtu.maaspservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Well {
    @Id
    @GeneratedValue
    private UUID id;
    private double PCSV;
    private double DSV;
    private double GA;
    private double GTBG;
    private double PCACC;
    private double DACC;
    private double PCPP;
    private double DPP;
    private double DFORM;
    private double FSFORM;
    private double PKR;
    private double PBLH;
    private double DLH;
    private double BFB;
    private double PCTBG;
    private double DSH;
    private double FSA;
    private double PBB;
}
