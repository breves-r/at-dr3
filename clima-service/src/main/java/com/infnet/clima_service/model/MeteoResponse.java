package com.infnet.clima_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeteoResponse {
    private Double maxTemperature;
    private Double minTemperature;
}
