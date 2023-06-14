package com.example.swalayan.repository;

import com.example.swalayan.model.IncomingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomingProductRepository extends JpaRepository<IncomingProduct,Long> {
}
