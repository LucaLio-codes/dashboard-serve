package com.psycho.dashboardserve.modules.weather.repositories;

import com.psycho.dashboardserve.modules.weather.models.HourlyWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourlyWeatherInfoRepository  extends JpaRepository<HourlyWeatherInfo, Long> {
}
