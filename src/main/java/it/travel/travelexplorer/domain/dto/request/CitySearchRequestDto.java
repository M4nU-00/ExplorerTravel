package it.travel.travelexplorer.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class CitySearchRequestDto {

    @NotBlank(message = "Nome città obbligatorio")
    private String nameCity;

    @NotBlank(message = "Nome regione obbligatorio")
    private String nameRegion;

    @NotBlank(message = "Nome Paese obbligatorio")
    private String nameCountry;
}
