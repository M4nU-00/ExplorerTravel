package it.travel.travelexplorer.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.travel.travelexplorer.domain.dto.request.CitySearchRequestDto;
import it.travel.travelexplorer.domain.dto.response.CityWithDetailsResponseDto;
import it.travel.travelexplorer.domain.entity.City;
import it.travel.travelexplorer.exception.base.BaseException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CityService {

    public List<City> findAll();

    public City findByUuid(UUID uuid) throws BaseException;

    public CityWithDetailsResponseDto findByNameAndRegionAndCountry(CitySearchRequestDto citySearchRequestDto)
            throws BaseException;

}
