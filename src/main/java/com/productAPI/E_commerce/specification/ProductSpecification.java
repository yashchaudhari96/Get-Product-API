package com.productAPI.E_commerce.specification;

import com.productAPI.E_commerce.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

    public class ProductSpecification {

        public static Specification<Product> filter(
                String category,
                Double minPrice,
                Double maxPrice,
                Double rating,
                String query,
                String search
        ) {

            return (root, criteriaQuery, cb) -> {

                List<Predicate> predicates = new ArrayList<>();

                if (category != null) {
                    predicates.add(
                            cb.equal(root.get("category"), category)
                    );
                }

                if (minPrice != null) {
                    predicates.add(
                            cb.greaterThanOrEqualTo(root.get("price"), minPrice)
                    );
                }

                if (maxPrice != null) {
                    predicates.add(
                            cb.lessThanOrEqualTo(root.get("price"), maxPrice)
                    );
                }

                if (rating != null) {
                    predicates.add(
                            cb.greaterThanOrEqualTo(root.get("rating"), rating)
                    );
                }

                if (search != null && !search.isEmpty()) {
                    predicates.add(
                            cb.like(
                                    cb.lower(root.get("title")),
                                    "%" + search.toLowerCase() + "%"
                            )
                    );
                }

                if (query != null && !query.isEmpty()) {
                    predicates.add(
                            cb.like(
                                    cb.lower(root.get("title")),
                                    "%" + query.toLowerCase() + "%"
                            )
                    );
                }

                return cb.and(predicates.toArray(new Predicate[0]));
            };
        }
    }