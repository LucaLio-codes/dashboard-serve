package com.psycho.dashboardserve.modules.weather.repositories;


import com.psycho.dashboardserve.modules.weather.models.MinutelyWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinutelyWeatherInfoRepository  extends JpaRepository<MinutelyWeatherInfo, Long> {
}
