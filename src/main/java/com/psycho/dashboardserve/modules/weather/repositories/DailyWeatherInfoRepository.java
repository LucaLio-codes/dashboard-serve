package com.psycho.dashboardserve.modules.weather.repositories;

import com.psycho.dashboardserve.modules.weather.models.DailyWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWeatherInfoRepository  extends JpaRepository<DailyWeatherInfo, Long> {
}
