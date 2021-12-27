package com.geekbrains.backend.test.db;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.CategoriesExample;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;
import com.github.javafaker.Faker;
import lombok.With;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Long.parseLong;

@With
public class TestDb {
    public static void main(String[] args) {
        Faker faker = new Faker();
        DbService dbService = new DbService();
        ProductsMapper productsMapper = dbService.getProductsMapper();
        CategoriesMapper categoriesMapper = dbService.getCategoriesMapper();

        Products product = new Products();
//        System.out.println(product);

        //Добавление еще двух категорий
        Categories categoryHH = new Categories();
        categoryHH.withId(3L).withTitle("Book");

        Categories categoryAvto = new Categories();
        categoryAvto.withId(4L).withTitle("Avtomotive");

        categoriesMapper.insert(categoryHH);
        categoriesMapper.insert(categoryAvto);

        CategoriesExample filterCategory = new CategoriesExample();
        List<Categories> cate = categoriesMapper.selectByExample(filterCategory);
        cate.forEach(System.out::println);
        filterCategory.clear();

        System.out.println("******************************************************************************************");

        //Добавление по 3 продукта в каждую категорию
        for ( Categories c : cate) {
            for (int i = 0; i < 3; i++) {
                Long id = parseLong(String.valueOf((int)(Math.random() * 300)));
                int price =  20 + (int) (Math.random() * 5000);
                product.withId(id)
                        .withPrice(price)
                        .withCategoryTitle(c.getId());
                if (c.getTitle().contains("Food")) {
                    product.withTitle(faker.food().ingredient());
                } else if (c.getTitle().contains("Book")) {
                    product.withTitle(faker.book().title());
                } else {
                    product.withTitle((faker.commerce().productName()));
                }
                productsMapper.insert(product);
            }
        }
        ProductsExample filterProducts = new ProductsExample();
        List<Products> products = productsMapper.selectByExample(filterProducts);
        products.forEach(System.out::println);
        filterProducts.clear();

        System.out.println("******************************************************************************************");
        //Вывести все товары каждой из категории
        List<Categories> categories = categoriesMapper.selectByExample(filterCategory);
        for (Categories c : categories) {
            System.out.println("id:" + c.getId() + " - " + c.getTitle());
            filterProducts.createCriteria().andCategoryIdEqualTo(c.getId());
            productsMapper.selectByExample(filterProducts).forEach(System.out::println);
            filterProducts.clear();
        }

        filterCategory.clear();
        System.out.println("******************************************************************************************");

        //Вывести топ самых дорогих продукта
        filterProducts.createCriteria().andPriceGreaterThan(1000);
        List<Products> listProd = productsMapper.selectByExample(filterProducts);
        listProd.sort(Comparator.comparing(Products::getPrice).reversed());

        IntStream.range(0, 3).mapToObj(listProd::get).forEach(System.out::println);
    }

}
