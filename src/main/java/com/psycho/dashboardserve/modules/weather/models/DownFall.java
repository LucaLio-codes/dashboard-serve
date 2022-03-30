package com.psycho.dashboardserve.modules.weather.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DownFall {
    private @Id @GeneratedValue long id;
    @JsonProperty("1h") @Getter @Setter double _1h;

}
