package it.travel.travelexplorer.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import it.travel.travelexplorer.domain.dto.request.CitySearchRequestDto;
import it.travel.travelexplorer.domain.dto.response.CityWithDetailsResponseDto;
import it.travel.travelexplorer.domain.entity.City;
import it.travel.travelexplorer.exception.ObjectNotFoundException;
import it.travel.travelexplorer.exception.base.BaseException;
import it.travel.travelexplorer.repository.CityRepository;
import it.travel.travelexplorer.repository.CountryRepository;
import it.travel.travelexplorer.repository.RegionRepository;
import it.travel.travelexplorer.service.CityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

        private final CityRepository cityRepository;
        private final CountryRepository countryRepository;
        private final RegionRepository regionRepository;

        public List<City> findAll() {
                return cityRepository.findAllByDeleteDateIsNull();
        }

        public City findByUuid(UUID uuid) throws BaseException {
                return cityRepository.findByUuidAndDeleteDateIsNull(uuid)
                                .orElseThrow(() -> new ObjectNotFoundException("City non trovata"));
        }

        public CityWithDetailsResponseDto findByNameAndRegionAndCountry(
                        CitySearchRequestDto citySearchRequestDto) throws BaseException {

                String nameRegion = citySearchRequestDto.getNameRegion().toUpperCase();
                String nameCountry = citySearchRequestDto.getNameCountry().toUpperCase();
                String nameCity = citySearchRequestDto.getNameCity().toUpperCase();

                // Check se esiste Region
                boolean isPresentRegion = regionRepository
                                .findByNameAndDeleteDateIsNull(nameRegion)
                                .isPresent();

                // Check se esiste Country
                boolean isPresentCountry = countryRepository
                                .findByNameAndDeleteDateIsNull(nameCountry)
                                .isPresent();

                if (!isPresentCountry || !isPresentRegion) {
                        throw new ObjectNotFoundException("Regione o Paese non presenti");
                }

                // Verifico se esiste la città
                City city = cityRepository.findByNameAndRegionAndCountry(nameCity, nameRegion, nameCountry)
                                .orElseThrow(() -> new ObjectNotFoundException("City Not Found"));

                // Costruisco l'oggetto di output
                return CityWithDetailsResponseDto.builder()
                                .uuidCountry(city.getCountry().getUuid())
                                .uuidRegion(city.getRegion().getUuid())
                                .uuid(city.getUuid())
                                .cityName(city.getName())
                                .countryName(nameCountry)
                                .regionName(nameRegion)
                                .latitude(city.getLatitude())
                                .longitude(city.getLongitude())
                                .build();
        }

}
