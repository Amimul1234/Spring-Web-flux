package com.reactive_spring.movies_info_service.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Amimul Ehsan
 * Date: 31/12/2021
 * Project Name : spring_reactive
 */

@Data
@Document
public class MovieInfo {
    @Id
    private String movieInfoId;
    private String name;
    private Integer year;
    private List<String> cast;
    private LocalDate releaseDate;
}
