package com.Kevin.BooksSearcherAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumoDatos implements IConsumoDatos{

    ObjectMapper leer= new ObjectMapper();
    public <T> T Consumo(String json, Class<T> objecto) {
        try{
            return leer.readValue(json,objecto);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
