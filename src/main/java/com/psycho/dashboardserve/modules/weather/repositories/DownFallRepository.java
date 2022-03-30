package com.psycho.dashboardserve.modules.weather.repositories;

import com.psycho.dashboardserve.modules.weather.models.DownFall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownFallRepository extends JpaRepository<DownFall, Long> {
}
