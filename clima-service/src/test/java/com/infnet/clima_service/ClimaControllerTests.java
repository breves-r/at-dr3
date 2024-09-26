package com.infnet.clima_service;

import com.infnet.clima_service.controller.ClimaController;
import com.infnet.clima_service.model.MeteoResponse;
import com.infnet.clima_service.service.ClimaWebClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@WebFluxTest(ClimaController.class)
public class ClimaControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ClimaWebClient climaWebClient;

    @Test
    public void testGetPrevisao() {
        Mono<MeteoResponse> mockResponse = Mono.just(new MeteoResponse(30.0, 20.0));
        Mockito.when(climaWebClient.getTemperaturas(anyDouble(), anyDouble())).thenReturn(mockResponse);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/clima")
                        .queryParam("latitude", -22.9035)
                        .queryParam("longitude", -43.2096)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(MeteoResponse.class)
                .value(response -> {
                    assert response.getMaxTemperature() == 30.0;
                    assert response.getMinTemperature() == 20.0;
                });
    }
}
