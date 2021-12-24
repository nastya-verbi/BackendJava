package com.geekbrains.backend.test.miniMarket;


import com.geekbrains.backend.test.miniMarket.model.Category;
import com.geekbrains.backend.test.miniMarket.model.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarketApi {

    @GET("categories/{id}")
    Call<Category> getCategory(@Path("id") Long id);

    @GET("products")
    Call<List<Product>> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") Long id);

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") Long id);

    @PUT("products")
    Call<Product> putProduct(@Body Product putProductRequest);


}
