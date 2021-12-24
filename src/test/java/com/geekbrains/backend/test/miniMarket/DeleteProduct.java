package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Product;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

@DisplayName("Удаление продукта")
public class DeleteProduct {
    static MiniMarketApi miniMarketApi;
    private Product product;
    private Faker faker;
    private Long expectedId;
    private String expectedTitle;
    private Integer expectedPrice;
    private String expectedCategoryTitle = "Food";

    @BeforeAll
    static void beforeAll() {
        miniMarketApi = MiniMarketService
                .getRetrofit()
                .create(MiniMarketApi.class);
    }

    @SneakyThrows
    @BeforeEach
    void setUp() {
        faker = new Faker();
        expectedTitle = faker.food().ingredient();
        expectedPrice = (int) (Math.random() * 1000);
        product = new Product()
                .withTitle(expectedTitle)
                .withPrice(expectedPrice)
                .withCategoryTitle(expectedCategoryTitle);
        miniMarketApi.createProduct(product).execute();
        Response<Product> resp = miniMarketApi.createProduct(product).execute();
        expectedId = resp.body().getId();
    }

    @SneakyThrows
    @Test
    @DisplayName("Удаление продукта по id")
    void deleteProductById() {
        //act
        Response<ResponseBody> response = miniMarketApi.deleteProduct(expectedId).execute();
        //assert
        Assertions.assertTrue(response.isSuccessful());
    }
}
