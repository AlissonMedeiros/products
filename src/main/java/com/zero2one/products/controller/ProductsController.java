package com.zero2one.products.controller;

import com.zero2one.products.model.Product;
import com.zero2one.products.reposioty.ProductRepository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    private Map<String, Product> products = new HashMap<>();

    public ProductsController() {
        Product celular = new Product();
        celular.setId("1A");
        celular.setName("Moto X");
        celular.setDescription("Novo Moto X 2");
        celular.setPrice(1999.99, 10D);

        Product notebook = new Product();
        notebook.setId("1B");
        notebook.setName("Dell");
        notebook.setDescription("Dell i9");
        notebook.setPrice(9999.99, 10D);

        products.put(celular.getId(), celular);
        products.put(notebook.getId(), notebook);
    }

    @GetMapping
    public Collection<Product> get() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") String id) {
        return products.get(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        products.put(product.getId(), product);
        return product;
    }

}
