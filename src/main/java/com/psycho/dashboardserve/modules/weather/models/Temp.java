package com.psycho.dashboardserve.modules.weather.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Temp {
    private @Id @GeneratedValue long id;
    @Getter @Setter private double day;
    @Getter @Setter private double min;
    @Getter @Setter private double max;
    @Getter @Setter private double night;
    @Getter @Setter private double eve;
    @Getter @Setter private double morn;
}
