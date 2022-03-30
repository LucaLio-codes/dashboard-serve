package com.psycho.dashboardserve.modules.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psycho.dashboardserve.modules.weather.models.Alert;
import com.psycho.dashboardserve.modules.weather.models.DownFall;
import com.psycho.dashboardserve.modules.weather.models.MinutelyWeatherInfo;
import com.psycho.dashboardserve.modules.weather.models.WeatherResponse;
import com.psycho.dashboardserve.modules.weather.repositories.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;

@Service
public class OpenWeatherService {


    private final String key;
    private final CurrentWatherInfoRepository currentWatherInfoRepository;
    private final DailyWeatherInfoRepository dailyWeatherInfoRepository;
    private final HourlyWeatherInfoRepository hourlyWeatherInfoRepository;
    private final MinutelyWeatherInfoRepository minutelyWeatherInfoRepository;
    private final TempRepository tempRepository;
    private final WeatherRepository weatherRepository;
    private final AlertRepository alertRepository;
    private final WeatherResponseRepository weatherResponseRepository;
    private final DownFallRepository downFallRepository;
    private final SimpMessagingTemplate template;
    @Setter @Getter private double lat, lon;


    @Autowired
    public OpenWeatherService(@Value("${key}") String key, @Value("${lat}") double lat, @Value("${lon}") double lon, CurrentWatherInfoRepository currentWatherInfoRepository, DailyWeatherInfoRepository dailyWeatherInfoRepository, HourlyWeatherInfoRepository hourlyWeatherInfoRepository, MinutelyWeatherInfoRepository minutelyWeatherInfoRepository, TempRepository tempRepository, WeatherRepository weatherRepository, AlertRepository alertRepository, WeatherResponseRepository weatherResponseRepository, DownFallRepository downFallRepository, SimpMessagingTemplate template) {
        this.currentWatherInfoRepository = currentWatherInfoRepository;
        this.dailyWeatherInfoRepository = dailyWeatherInfoRepository;
        this.hourlyWeatherInfoRepository = hourlyWeatherInfoRepository;
        this.minutelyWeatherInfoRepository = minutelyWeatherInfoRepository;
        this.tempRepository = tempRepository;
        this.weatherRepository = weatherRepository;
        this.alertRepository = alertRepository;
        this.key = key;
        this.weatherResponseRepository = weatherResponseRepository;
        this.downFallRepository = downFallRepository;
        this.lat = lat;
        this.lon = lon;
        this.template = template;
    }

    @Scheduled(fixedRate =   5 * 1000)
    public WeatherResponse fetch() throws IOException, JSONException {
        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=" + lat
                + "&lon=" + lon
                + "&units=metric&appid=" + this.key;
        // System.out.println(urlString);
        URL url = new URL(urlString);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONObject json = new JSONObject(content.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse response = objectMapper.readValue(json.toString(), WeatherResponse.class);
        weatherRepository.saveAll(response.getCurrent().getWeather());
        DownFall rain = response.getCurrent().getRain();
        DownFall snow = response.getCurrent().getSnow();
        if (rain != null)
            downFallRepository.save(rain);
        if (snow != null)
            downFallRepository.save(snow);
        currentWatherInfoRepository.save(response.getCurrent());
        Set<MinutelyWeatherInfo> minutely = response.getMinutely();
        if (minutely != null)
            minutelyWeatherInfoRepository.saveAll(minutely);
        response.getHourly().forEach(x -> {
            weatherRepository.saveAll(x.getWeather());

            DownFall xrain = x.getRain();
            DownFall xsnow = x.getSnow();
            if (xrain != null)
                downFallRepository.save(xrain);
            if (xsnow != null)
                downFallRepository.save(xsnow);

            hourlyWeatherInfoRepository.save(x);
        });
        response.getDaily().forEach(x -> {
            weatherRepository.saveAll(x.getWeather());
            tempRepository.save(x.getTemp());
            tempRepository.save(x.getFeels_like());
            dailyWeatherInfoRepository.save(x);
        });
        Set<Alert> alerts = response.getAlerts();
        if (alerts != null && !alerts.isEmpty()) {
            alertRepository.saveAll(alerts);
        }
        weatherResponseRepository.save(response);
        this.template.convertAndSend("/topic/weather", response);
        return response;
    }

}
