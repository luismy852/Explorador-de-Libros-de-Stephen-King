package com.bruno.stephenkingapi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosVillano(
        @JsonAlias("id") Integer id,
        @JsonAlias("name") String nombre,
        @JsonAlias("gender") String genero,
        @JsonAlias("status") String estado
) {
}
