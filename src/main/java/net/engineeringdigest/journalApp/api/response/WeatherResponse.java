package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class WeatherResponse{

    private Current current;

    @Data
    public class Current{
        public String observation_time;
        public int temperature;
        @JsonProperty("weather_descriptions")
        public List<String>weatherDescriptions ;
        public int feelslike;
    }

}





