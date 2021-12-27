package com.geekbrains.backend.test.miniMarket.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Data
public class Category {
    Long id;
    List<Product> products = new ArrayList<>();
    String title;
}
