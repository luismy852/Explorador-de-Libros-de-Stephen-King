package com.bruno.stephenkingapi.service;

public interface IDatos {
    <T> T obtenerDatos (String json, Class<T> clase);
}
