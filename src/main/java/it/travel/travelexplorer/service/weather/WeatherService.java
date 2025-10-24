package it.travel.travelexplorer.service.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import it.travel.travelexplorer.domain.dto.request.WeatherRequestDto;
import it.travel.travelexplorer.domain.dto.response.WeatherResponseDto;
import it.travel.travelexplorer.domain.util.CountryCodeMapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherResponseDto getWeatherByCity(WeatherRequestDto weatherRequestDto) {
        String url = buildWeatherUrl(weatherRequestDto);

        log.info("URL formata: {}", url);

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            JsonNode body = response.getBody();

            WeatherResponseDto dto = new WeatherResponseDto();
            dto.setCityName(body.get("name").asText());
            dto.setTemperature(body.get("main").get("temp").asDouble());
            dto.setDescription(body.get("weather").get(0).get("description").asText());
            dto.setHumidity(body.get("main").get("humidity").asInt());
            dto.setWindSpeed(body.get("wind").get("speed").asDouble());

            return dto;
        }

        throw new RuntimeException("Errore nel recupero dei dati meteo");
    }

    // Metodo private per costruire la url per la restituzione dei dati meteo
    private String buildWeatherUrl(WeatherRequestDto weatherRequestDto) {
        Double lat = weatherRequestDto.getLatitude();
        Double lon = weatherRequestDto.getLongitude();
        String city = weatherRequestDto.getCity();
        String countryCode = CountryCodeMapUtil.COUNTRY_CODES.get(weatherRequestDto.getCountryName().toUpperCase());

        String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
        StringBuilder url = new StringBuilder(baseUrl + "?appid=" + apiKey + "&units=metric&lang=it");

        if (lat != null && lon != null) {
            url.append("&lat=").append(lat);
            url.append("&lon=").append(lon);
        }

        if (city != null && countryCode != null) {
            url.append("&q=").append(city).append(",").append(countryCode);
        } else if (city != null) {
            url.append("&q=").append(city);
        }

        return url.toString();
    }

}