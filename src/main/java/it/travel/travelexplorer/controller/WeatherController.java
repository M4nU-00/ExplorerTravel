package it.travel.travelexplorer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.travel.travelexplorer.domain.dto.request.WeatherRequestDto;
import it.travel.travelexplorer.domain.dto.response.WeatherResponseDto;
import it.travel.travelexplorer.exception.base.BaseException;
import it.travel.travelexplorer.service.weather.WeatherService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping()
    public WeatherResponseDto getWeatherByCity(@RequestBody WeatherRequestDto weatherRequestDto,
            @RequestHeader("Authorization") String authHeader) throws BaseException {
        String token = authHeader.replace("Bearer ", "");
        return weatherService.getWeatherByCity(weatherRequestDto, token);
    }
}
