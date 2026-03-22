package com.productAPI.E_commerce.config;

import com.productAPI.E_commerce.entity.Product;
import com.productAPI.E_commerce.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class DataLoader {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void loadData() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://dummyjson.com/products?limit=100";

        Map response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> products =
                (List<Map<String, Object>>) response.get("products");

        for (Map<String, Object> p : products) {

            Product product = new Product();
            product.setId(((Number) p.get("id")).longValue());
            product.setTitle((String) p.get("title"));
            product.setCategory((String) p.get("category"));
            product.setPrice(((Number) p.get("price")).doubleValue());
            product.setRating(((Number) p.get("rating")).doubleValue());
            product.setThumbnail((String) p.get("thumbnail"));

            productRepository.save(product);
        }
    }

}