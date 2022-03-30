package com.psycho.dashboardserve.modules.weather.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HourlyWeatherInfo {
    private @Id @GeneratedValue
    Long id;
    @Getter @Setter long dt;
    @Getter @Setter double temp;
    @Getter @Setter double feels_like;
    @Getter @Setter long pressure;
    @Getter @Setter int humidity;
    @Getter @Setter private double dew_point;
    @Getter @Setter private double uvi;
    @Getter @Setter private int clouds;
    @Getter @Setter private int visibility;
    @Getter @Setter private double wind_speed;
    @Getter @Setter private int wind_deg;
    @Getter @Setter private double wind_gust;
    @Getter @Setter private @ManyToMany( fetch= FetchType.EAGER)
    Set<Weather> weather;
    @Getter @Setter private @OneToOne( fetch= FetchType.EAGER) DownFall rain;
    @Getter @Setter private @OneToOne( fetch= FetchType.EAGER) DownFall snow;
    @Getter @Setter private int pop;

}
