package com.psycho.dashboardserve.modules.weather.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
public class WeatherResponse {

    @Getter private @Id @GeneratedValue Long id;
    @Getter @Setter private double lat;
    @Getter @Setter private double lon;
    @Getter @Setter private String timezone;
    @Getter @Setter private int timezone_offset;
    @Getter @Setter private @OneToOne( fetch= FetchType.EAGER) CurrentWeatherInfo current;
    @Getter @Setter private @OneToMany( fetch= FetchType.EAGER) Set<MinutelyWeatherInfo> minutely;
    @Getter @Setter private @OneToMany( fetch= FetchType.EAGER) Set<HourlyWeatherInfo> hourly;
    @Getter @Setter private @OneToMany ( fetch= FetchType.EAGER)Set<DailyWeatherInfo> daily;
    @Getter @Setter private @OneToMany( fetch= FetchType.EAGER) Set<Alert> alerts;
    
    public WeatherResponse(){}

    WeatherResponse(double lat, double lon, String timezone, int timezone_offset, CurrentWeatherInfo current,
                    Set<MinutelyWeatherInfo> minutely, Set<HourlyWeatherInfo> hourly, Set<DailyWeatherInfo> daily,
                    Set<Alert> alerts){
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.timezone_offset = timezone_offset;
        this.current = current;
        this.minutely = minutely;
        this.hourly = hourly;
        this.daily = daily;
        this.alerts = alerts;
    }

}
