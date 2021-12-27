package com.geekbrains.db.model;

import com.geekbrains.backend.test.miniMarket.model.Product;

public class Products {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS.ID
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS.TITLE
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS.PRICE
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    private Integer price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PRODUCTS.CATEGORY_ID
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    private Long categoryId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS.ID
     *
     * @return the value of PRODUCTS.ID
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS.ID
     *
     * @param id the value for PRODUCTS.ID
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS.TITLE
     *
     * @return the value of PRODUCTS.TITLE
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS.TITLE
     *
     * @param title the value for PRODUCTS.TITLE
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS.PRICE
     *
     * @return the value of PRODUCTS.PRICE
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS.PRICE
     *
     * @param price the value for PRODUCTS.PRICE
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PRODUCTS.CATEGORY_ID
     *
     * @return the value of PRODUCTS.CATEGORY_ID
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PRODUCTS.CATEGORY_ID
     *
     * @param categoryId the value for PRODUCTS.CATEGORY_ID
     *
     * @mbg.generated Sat Dec 25 22:48:48 GMT+04:00 2021
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Products withId(Long id) {
        this.id = id;
        return this;
    }

    public Products withTitle(String title) {
        this.title = title;
        return this;
    }

    public Products withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Products withCategoryTitle(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }
}