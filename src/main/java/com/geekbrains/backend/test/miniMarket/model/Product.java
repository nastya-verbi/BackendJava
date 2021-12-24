package com.geekbrains.backend.test.miniMarket.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@NoArgsConstructor
@With
public class Product {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("categoryTitle")
    @Expose
    private String categoryTitle;

    public Product withId(Long id) {
        this.id = id;
        return this;
    }

    public Product withTitle(String title) {
        this.title = title;
        return this;
    }

    public Product withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Product withCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}
