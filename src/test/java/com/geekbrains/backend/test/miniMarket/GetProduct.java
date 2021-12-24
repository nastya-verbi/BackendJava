package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Product;
import lombok.SneakyThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.List;

@DisplayName("Вызов полного списка продуктов")
public class GetProduct {
    static MiniMarketApi miniMarketApi;


    @BeforeAll
    static void beforeAll() {
        miniMarketApi = MiniMarketService
                .getRetrofit()
                .create(MiniMarketApi.class);
    }


    @SneakyThrows
    @Test
    @DisplayName("Вернуть все товары в списке")
    void getProducts() {
        //act
        Response<List<Product>> response = miniMarketApi.getProducts().execute();
        //assert
        Assertions.assertTrue(response.isSuccessful(), "Появляется код ошибки - " + response.code());
    }
}
