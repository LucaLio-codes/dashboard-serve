package com.psycho.dashboardserve.modules.weather.repositories;

import com.psycho.dashboardserve.modules.weather.models.CurrentWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentWatherInfoRepository extends JpaRepository<CurrentWeatherInfo, Long> {
}
