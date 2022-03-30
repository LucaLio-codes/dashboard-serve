package com.psycho.dashboardserve.modules.weather;

import com.psycho.dashboardserve.modules.weather.models.WeatherResponse;
import com.psycho.dashboardserve.modules.weather.repositories.*;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    private final OpenWeatherService openWeatherService;
    private final WeatherResponseRepository weatherResponseRepository;

    public WeatherController(OpenWeatherService openWeatherService, WeatherResponseRepository weatherResponseRepository, CurrentWatherInfoRepository currentWatherInfoRepository, DailyWeatherInfoRepository dailyWeatherInfoRepository, HourlyWeatherInfoRepository hourlyWeatherInfoRepository, MinutelyWeatherInfoRepository minutelyWeatherInfoRepository, TempRepository tempRepository, WeatherRepository weatherRepository, AlertRepository alertRepository) {
        this.openWeatherService = openWeatherService;
        this.weatherResponseRepository = weatherResponseRepository;
    }

    @GetMapping("/fetch")
    ResponseEntity<WeatherResponse> fetch(){
        try {
            WeatherResponse response = this.openWeatherService.fetch();
            update();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new WeatherResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    ResponseEntity<List<WeatherResponse>> getAll(){
        return new ResponseEntity<>(weatherResponseRepository.findAll(), HttpStatus.OK);
    }


    @MessageMapping("/latest")
    @SendTo("/topic/weather")
    public WeatherResponse update(){
        return weatherResponseRepository.getLatest();
    }



}
