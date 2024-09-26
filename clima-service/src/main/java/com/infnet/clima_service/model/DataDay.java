package com.infnet.clima_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DataDay {
    @JsonProperty("temperature_max")
    private List<Double> temperatureMax;

    @JsonProperty("temperature_min")
    private List<Double> temperatureMin;
}
