package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.API.Response.WeatherResponse;
import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.Constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {


    @Value("${weather.api.key}")
    private String apikey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
           return weatherResponse;
        } else {
            String finalAPI = appCache.appCache.get(AppCache.keys.weather_api.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
}
//deserialization= The process of converting JSON response in corresponding java obejct is called deserialization.