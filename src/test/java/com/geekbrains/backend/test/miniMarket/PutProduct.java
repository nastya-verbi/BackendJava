package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Product;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

@DisplayName("Change product")
public class PutProduct {
    static MiniMarketApi miniMarketApi;
    Faker faker = new Faker();
    private Product product;
    private String expectedTitle;
    private Integer expectedPrice = (int) (Math.random() * 1000);
    private Long expectedId = 2L;

    @BeforeAll
    static void beforeAll() {
        miniMarketApi = MiniMarketService
                .getRetrofit()
                .create(MiniMarketApi.class);
    }

    @BeforeEach
    void setUp() {
        expectedTitle = faker.food().ingredient();
        product = new Product()
                .withId(expectedId)
                .withTitle(expectedTitle)
                .withPrice(expectedPrice)
                .withCategoryTitle("Food");
    }

    @SneakyThrows
    @Test
    @DisplayName("Change tovar")
    void putProduct() {
        //act
        Response<Product> response = miniMarketApi.putProduct(product).execute();
        //assert
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(expectedId, response.body().getId(), "Неверный id");
        Assertions.assertEquals(expectedTitle, response.body().getTitle(), "Неверный title продукта");
        Assertions.assertEquals(expectedPrice, response.body().getPrice(), "Неверный price продукта");
    }
}
