package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Product;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import retrofit2.Response;


@DisplayName("Создание продукта")
public class CreateProduct {
    static MiniMarketApi miniMarketApi;
    private Product product;
    private Long id;
    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        miniMarketApi = MiniMarketService
                .getRetrofit()
                .create(MiniMarketApi.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int)(Math.random()* 10000));
    }

    @SneakyThrows
    @Test
    @DisplayName("Создание продукта в категории Food")
    void createProductInFoodCategoryTest() {
        //act
        Response<Product> response = miniMarketApi.createProduct(product).execute();
        id = response.body().getId();
        //assert
        Assertions.assertTrue(response.isSuccessful(), "Появляется код ошибки - " + response.code());
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = miniMarketApi.deleteProduct(id).execute();
        Assertions.assertTrue(response.isSuccessful());
    }
}
