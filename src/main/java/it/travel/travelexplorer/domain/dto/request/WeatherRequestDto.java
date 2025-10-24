package it.travel.travelexplorer.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherRequestDto {

    private String city;
    private String countryName; // es. "it"
    private Double latitude;
    private Double longitude;

}
