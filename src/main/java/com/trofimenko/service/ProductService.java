package com.trofimenko.service;

import com.trofimenko.entites.Person;
import com.trofimenko.entites.Product;
import com.trofimenko.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProductsList(){
        return (List<Product>) productRepository.findAll();
    }

    @Transactional
    public void addProduct(Product p){
        productRepository.save(p);
    }

    public Product getProductById(Integer id){
        return productRepository.getProductById(id);
    }

    @Transactional
    public void removeById(Integer id){
        productRepository.deleteById(id);
    }

    public List<Product> findProductByCost(int cost){
        return productRepository.findProductByCost(cost);
    }

    public List<Integer> findId(int cost){
        return productRepository.findID(cost);
    }




}
