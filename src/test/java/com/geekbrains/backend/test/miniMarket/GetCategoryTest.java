package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.Category;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

@DisplayName("Вызов категории товаров")
public class GetCategoryTest {
    static MiniMarketApi miniMarket;

    @BeforeAll
    static void beforeAll() {
        miniMarket = MiniMarketService.getRetrofit().create(MiniMarketApi.class);
    }

    @SneakyThrows
    @Test
    @DisplayName("Проверка существующей категории")
    void getCategoryByIdPositiveTest() {
        //arrange
        Long id = 1L;
        //act
        Response<Category> response = miniMarket.getCategory(id).execute();
        //assert
        Assertions.assertTrue(response.isSuccessful());
    }

    @SneakyThrows
    @Test
    @DisplayName("Проверка несуществующей категории")
    void getCategoryByInvalidId() {
        //arrange
        Long id = 20L;
        int expectedStatus = 404;
        //act
        Response<Category> response = miniMarket.getCategory(id).execute();
        //assert
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertEquals(expectedStatus, response.code());
    }
}
