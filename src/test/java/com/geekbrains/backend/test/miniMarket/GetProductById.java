package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Product;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;

import okhttp3.ResponseBody;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import retrofit2.Response;


@DisplayName("Вызов одного продукта")
public class GetProductById {
    static MiniMarketApi miniMarketApi;
    private Product product;
    Faker faker;

    private static Long expectedId;
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


    @org.junit.jupiter.api.Test
    @DisplayName("Получить продукт по id")
    @SneakyThrows
    void getProductById() {
        //act
        Response<Product> response = miniMarketApi.getProduct(expectedId).execute();
        //assert
        Assertions.assertTrue(response.isSuccessful(), "Появляется код ошибки - " + response.code());
        Assertions.assertEquals(expectedId, response.body().getId(), "Неверный id");
        Assertions.assertEquals(expectedTitle, response.body().getTitle(), "Неверный title продукта");
        Assertions.assertEquals(expectedPrice, response.body().getPrice(), "Неверный price продукта");
    }

    @Test
    @DisplayName("Вввод некорректного id")
    @SneakyThrows
    void getProductInvalidId() {
        //act
        Response<Product> response = miniMarketApi.getProduct(1000L).execute();
        //assert
        int expectedCodeError = 404;
        Assertions.assertFalse(response.isSuccessful(), "появляется код - " + response.code());
        Assertions.assertEquals(expectedCodeError, response.code(), "Код ошибки не совпадает");
    }

    @SneakyThrows
    @AfterAll
    static void tearDown() {
        Response<ResponseBody> response = miniMarketApi.deleteProduct(expectedId).execute();
        Assertions.assertTrue(response.isSuccessful());
    }
}
