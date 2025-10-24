package it.travel.travelexplorer.domain.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityWithDetailsResponseDto {

    private UUID uuid;
    private String cityName;
    private Double latitude;
    private Double longitude;

    private UUID uuidCountry;
    private String countryName;

    private UUID uuidRegion;
    private String regionName;

}
