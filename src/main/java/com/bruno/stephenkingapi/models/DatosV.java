package com.bruno.stephenkingapi.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosV(
        @JsonAlias("data") List<DatosVillano> resultados
) {
}
