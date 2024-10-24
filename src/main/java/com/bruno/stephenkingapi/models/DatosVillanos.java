package com.bruno.stephenkingapi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosVillanos(
        @JsonAlias("name") String nombreVillano,
        @JsonAlias("url") String url
) {
}
