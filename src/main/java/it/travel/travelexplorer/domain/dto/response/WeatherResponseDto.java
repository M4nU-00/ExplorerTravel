package it.travel.travelexplorer.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class WeatherResponseDto {

    private String cityName;
    private Double temperature;
    private String description;
    private Integer humidity;
    private Double windSpeed;

}
