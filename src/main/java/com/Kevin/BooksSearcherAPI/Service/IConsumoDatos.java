package com.Kevin.BooksSearcherAPI.Service;

public interface IConsumoDatos {

    public  <T> T Consumo(String json,Class <T> object );
}
