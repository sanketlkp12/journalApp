package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    //private static  final  String apiKey = "efe5351198f4f3b0e657627765ba2ec1";

    @Value("${weather.api.key}")
    private String apiKey;

    /*Fetching API from db using @PostConstruct in AppCache.class*/
   // private static final String API = "http://api.weatherstack.com/current?access_key=cd32c0a74b2ff4012b6b2a396991a349&query=Mumbai";

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }
        else{

            String finalAPI = appCache.APP_CACHE.get("weather_api").replace(PlaceHolders.CITY, city).replace(PlaceHolders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);//Deserializtionn from JSON to Java in WeatherResponse.class
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("Weather_of_" + city,body,300l);
            }
            return body;
        }
    }
}
