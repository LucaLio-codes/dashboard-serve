package com.psycho.dashboardserve.modules.weather.repositories;

import com.psycho.dashboardserve.modules.weather.models.Temp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository  extends JpaRepository<Temp, Long> {
}
