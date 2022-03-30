package com.psycho.dashboardserve.modules.weather.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Alert {
    private @Id @GeneratedValue long id;
    private @ManyToOne
    WeatherResponse weatherResponse;
}
