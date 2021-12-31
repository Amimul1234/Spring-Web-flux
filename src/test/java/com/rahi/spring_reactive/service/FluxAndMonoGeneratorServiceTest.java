package com.rahi.spring_reactive.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

/**
 * Created by Amimul Ehsan
 * Date: 31/12/2021
 * Project Name : spring_reactive
 */


public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {

        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void namesFlux_map() {

        var nameFlux = fluxAndMonoGeneratorService.namesFlux_map(3);

        StepVerifier.create(nameFlux)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFlux_immutability() {

        var nameFlux = fluxAndMonoGeneratorService.namesFlux_immutability();

        StepVerifier.create(nameFlux)
                .expectNext("alex", "ben", "chloe")
                .verifyComplete();
    }


    @Test
    void namesFlux_flatMap() {

        int stringLength = 3;

        var namesFlux = fluxAndMonoGeneratorService
                .namesFlux_flatMap(stringLength);

        StepVerifier.create(namesFlux)
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFlux_concatMap() {

        int stringLength = 3;

        var namesFlux = fluxAndMonoGeneratorService
                .namesFlux_concatMap(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesMonoFlatMap() {

        int stringLength = 3;

        var value = fluxAndMonoGeneratorService.namesMono_flatMap(stringLength);

        StepVerifier.create(value)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void namesFlux_transform() {

        var namesFlux = fluxAndMonoGeneratorService
                .namesFlux_transform();

        StepVerifier.create(namesFlux)
                .expectNextCount(12)
                .verifyComplete();
    }

    @Test
    void explore_concat() {

        var concatFlux = fluxAndMonoGeneratorService.explore_concat();

        StepVerifier.create(concatFlux)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void explore_concatWith() {

        var concatFlux = fluxAndMonoGeneratorService.explore_concatWith();

        StepVerifier.create(concatFlux)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void explore_concatWithMono() {

        var concatFlux = fluxAndMonoGeneratorService.explore_concatWithMono();

        StepVerifier.create(concatFlux)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();
    }
}
