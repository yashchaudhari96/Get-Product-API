package com.productAPI.E_commerce.service;

import com.productAPI.E_commerce.entity.Product;
import com.productAPI.E_commerce.repository.ProductRepository;
import com.productAPI.E_commerce.specification.ProductSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository repository;

    public Page<Product> getProducts(
            int page, int size, String sortBy, String category, Double minPrice, Double maxPrice, Double rating,String query, String search
    ) {

        logger.debug("Executing product search with filters");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Specification<Product> spec =
                ProductSpecification.filter(category, minPrice, maxPrice, rating,query, search);

        return repository.findAll(spec, pageable);
    }
}