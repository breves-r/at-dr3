package com.infnet.clima_service.controller;

import com.infnet.clima_service.model.MeteoResponse;
import com.infnet.clima_service.service.ClimaWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clima")
public class ClimaController {
    @Autowired
    private ClimaWebClient climaWebClient;

    @GetMapping
    public Mono<MeteoResponse> getPrevisao(@RequestParam double latitude, @RequestParam double longitude) {
        return climaWebClient.getTemperaturas(latitude, longitude);
    }
}