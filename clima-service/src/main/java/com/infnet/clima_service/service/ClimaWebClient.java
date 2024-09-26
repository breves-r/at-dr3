package com.infnet.clima_service.service;

import com.infnet.clima_service.model.ApiResponse;
import com.infnet.clima_service.model.MeteoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClimaWebClient {
    private final WebClient webClient;

    public ClimaWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://my.meteoblue.com").build();
    }

    public Mono<MeteoResponse> getTemperaturas(double latitude, double longitude) {
        String apiKey = "XhMCJbByP32uONSI";

        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/packages/basic-1h_basic-day")
                        .queryParam("apikey", apiKey)
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("format", "json")
                        .build()
                )
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .map(apiResponse  -> {
                    Double maxTemperature = apiResponse.getDataDay().getTemperatureMax().get(0);
                    Double minTemperature = apiResponse.getDataDay().getTemperatureMin().get(0);
                    return new MeteoResponse(maxTemperature, minTemperature);
                });
    }
}

