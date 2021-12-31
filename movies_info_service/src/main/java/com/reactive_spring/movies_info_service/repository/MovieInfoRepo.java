package com.reactive_spring.movies_info_service.repository;

import com.reactive_spring.movies_info_service.domain.MovieInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Amimul Ehsan
 * Date: 31/12/2021
 * Project Name : spring_reactive
 */

@Repository
public interface MovieInfoRepo extends ReactiveMongoRepository<MovieInfo, String> {
}
