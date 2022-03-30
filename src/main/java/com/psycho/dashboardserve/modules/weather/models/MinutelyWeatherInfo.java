package com.psycho.dashboardserve.modules.weather.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MinutelyWeatherInfo {
    private @Id @GeneratedValue
    Long id;
    @Getter @Setter long dt;
    @Getter @Setter int precipitation;

}
