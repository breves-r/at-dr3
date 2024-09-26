package com.infnet.clima_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResponse {
    @JsonProperty("data_day")
    private DataDay dataDay;
}
