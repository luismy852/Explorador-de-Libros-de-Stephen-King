package com.bruno.stephenkingapi.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibros(
        @JsonAlias("id") Integer id,
        @JsonAlias("Year") Integer añoLanzamiento,
        @JsonAlias("Title") String titulo,
        @JsonAlias("Pages") Integer paginas,
        @JsonAlias("villains") List<DatosVillanos> villanos

) {
}
