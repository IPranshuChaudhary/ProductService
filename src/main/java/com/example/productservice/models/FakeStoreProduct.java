package com.example.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProduct {

        private Long id;
        private String title;
        private String price;

        private String category;

        private String description;
        private String imageUrl;

}
