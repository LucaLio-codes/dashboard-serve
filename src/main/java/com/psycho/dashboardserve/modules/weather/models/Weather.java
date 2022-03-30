package com.psycho.dashboardserve.modules.weather.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {
   @Getter @Setter private  @Id long id;
   @Getter @Setter private String main;
   @Getter @Setter private String description;
   @Getter @Setter private String icon;
}
