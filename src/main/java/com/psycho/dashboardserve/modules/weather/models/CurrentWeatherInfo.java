package com.psycho.dashboardserve.modules.weather.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CurrentWeatherInfo {
    @Getter @Setter private @Id @GeneratedValue long id;
    @Getter @Setter private long dt;
    @Getter @Setter private long sunrise;
    @Getter @Setter private long sunset;
    @Getter @Setter private double temp;
    @Getter @Setter private double feels_like;
    @Getter @Setter private long pressure;
    @Getter @Setter private int humidity;
    @Getter @Setter private double dew_point;
    @Getter @Setter private double uvi;
    @Getter @Setter private int clouds;
    @Getter @Setter private int visibility;
    @Getter @Setter private int wind_speed;
    @Getter @Setter private int wind_deg;
    @Getter @Setter private double wind_gust;
    @Getter @Setter private @ManyToMany(fetch= FetchType.EAGER) Set<Weather> weather;
    @Getter @Setter private @OneToOne(fetch= FetchType.EAGER) DownFall rain;
    @Getter @Setter private @OneToOne(fetch= FetchType.EAGER) DownFall snow;
}
