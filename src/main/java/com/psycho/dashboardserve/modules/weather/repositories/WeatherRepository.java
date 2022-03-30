package com.psycho.dashboardserve.modules.weather.repositories;


import com.psycho.dashboardserve.modules.weather.models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository  extends JpaRepository<Weather, Long> {
}
