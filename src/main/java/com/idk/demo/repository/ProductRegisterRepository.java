package com.idk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idk.demo.model.Product;

@Repository
public interface ProductRegisterRepository extends JpaRepository<Product, Long>{

}
