package com.trofimenko.repositories;

import com.trofimenko.entites.Person;
import com.trofimenko.entites.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    Product getProductById(Integer id);
    List<Product>getProductsByCostGreaterThan(int cost);

    @Query("select p from Product p where p.cost = :cost")
    List<Product> findProductByCost(@Param("cost")int cost);

    @Query(value = "select id from products where cost = ?1", nativeQuery = true)
    List<Integer> findID(int cost);






}
