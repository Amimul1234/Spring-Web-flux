package com.reactive_spring.movies_info_service.repository;

import com.reactive_spring.movies_info_service.domain.MovieInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Amimul Ehsan
 * Date: 01/01/2022
 * Project Name : spring_reactive
 */

@DataMongoTest
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class MovieInfoRepoIntegrationTest {

    @Autowired
    MovieInfoRepo movieInfoRepo;

    @BeforeEach
    void setUp() {
        System.out.println("BeforeEach() executed ... ");
        var movieInfos = List.of(
                new MovieInfo(null, "Batman Begins", 2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knights", 2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo(null, "Batman Begins", 2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        movieInfoRepo.saveAll(movieInfos)
                .blockLast();
    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach() executed ... ");
        movieInfoRepo.deleteAll().block();
    }

    @Test
    void findAll() {

        var moviesInfoFlux = movieInfoRepo.findAll();

        StepVerifier
                .create(moviesInfoFlux)
                .expectNextCount(3)
                .verifyComplete();
    }
}