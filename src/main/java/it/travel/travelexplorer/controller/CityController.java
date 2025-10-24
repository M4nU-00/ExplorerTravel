package it.travel.travelexplorer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.travel.travelexplorer.domain.dto.request.CitySearchRequestDto;
import it.travel.travelexplorer.domain.dto.response.CityWithDetailsResponseDto;
import it.travel.travelexplorer.domain.entity.City;
import it.travel.travelexplorer.exception.base.BaseException;
import it.travel.travelexplorer.service.CityService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/city")
class CityController {
    private final CityService cityService;

    @GetMapping
    public List<City> getAll() {
        return cityService.findAll();
    }

    @GetMapping("/{uuid}")
    public City getByUuid(@PathVariable UUID uuid) throws BaseException {
        return cityService.findByUuid(uuid);
    }

    @PostMapping("/search")
    public CityWithDetailsResponseDto getMethodName(@RequestBody CitySearchRequestDto citySearchRequestDto)
            throws BaseException {
        return cityService.findByNameAndRegionAndCountry(citySearchRequestDto);
    }

   
}