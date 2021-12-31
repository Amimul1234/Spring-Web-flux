package com.rahi.spring_reactive.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Created by Amimul Ehsan
 * Date: 31/12/2021
 * Project Name : spring_reactive
 */

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(
                List.of("alex", "ben", "chloe")
        );
    }

    public Mono<String> nameMono() {
        return Mono.just("alex");
    }

    public Flux<String> namesFlux_map( int stringLength ) {

        // Filter the string whose length is greater than 3
        return Flux.fromIterable(
                        List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + "-" + s);
    }

    public Flux<String> namesFlux_immutability() {

        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));

        namesFlux.map(String::toUpperCase);

        return namesFlux();
    }

    public Flux<String> namesFlux_flatMap( int stringLength ) {
        return Flux.fromIterable(
                        List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> namesFlux_flatMapAsync( int stringLength ) {

        return Flux.fromIterable(
                        List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitString);

    }

    public Flux<String> splitString( String name ) {

        var charArray = name.split("");

        int i = new Random().nextInt(1000);

        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(i));
    }

    public Flux<String> namesFlux_concatMap( int stringLength ) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .concatMap(this::splitString)
                .log();
    }

    //Use concat map where ordering matters most. Concat map will behave like sequential
    public Mono<List<String>> namesMono_flatMap( int stringLength ) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono);
    }

    private Mono<List<String>> splitStringMono( String s ) {
        var charArray = s.split("");
        var charList = List.of(charArray);
        return Mono.just(charList);
    }

    public Flux<String> namesFlux_transform() {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase);

        return Flux.fromIterable(
                        List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> explore_concat() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.concat(abcFlux, defFlux);
    }

    public Flux<String> explore_concatWith() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.concatWith(defFlux);
    }

    public Flux<String> explore_concatWithMono() {

        var aMono = Mono.just("A");
        var bMono = Mono.just("B");
        var cMono = Mono.just("C");
        var dMono = Mono.just("D");

        return aMono.concatWith(bMono).concatWith(cMono)
                .concatWith(dMono);
    }

    //Concat adds subscriber in a sequential manner and merge adds the publisher at the same time

    //Zip and zipWith
    public Flux<String> explore_zip() {

        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.zip(abcFlux, defFlux, ( first, second ) -> first + second);
    }


    public static void main( String[] args ) {

        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService
                .namesFlux()
                .subscribe(name -> System.out.println("Name is : " + name));

        fluxAndMonoGeneratorService
                .nameMono()
                .subscribe(name -> System.out.println("Name is : " + name));
    }
}
