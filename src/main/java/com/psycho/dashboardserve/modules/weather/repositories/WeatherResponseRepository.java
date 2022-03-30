package com.psycho.dashboardserve.modules.weather.repositories;

import com.psycho.dashboardserve.modules.weather.models.WeatherResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WeatherResponseRepository extends JpaRepository<WeatherResponse, Long> {

    @Query("SELECT w from WeatherResponse w WHERE w.id = (SELECT max(id)from WeatherResponse )")
    WeatherResponse getLatest();
}
