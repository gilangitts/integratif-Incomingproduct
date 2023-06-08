package com.example.swalayan.repository;

import com.example.swalayan.model.IncomingProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomingProductService {

    @Autowired
    private IncomingProductRepository incomingProductRepository;

    public List<IncomingProduct> getAllIncomingProducts() {
        return incomingProductRepository.findAll();
    }

    public IncomingProduct getIncomingProductById(int id) {
        return incomingProductRepository.findById(id).orElse(null);
    }

    public void addIncomingProduct(IncomingProduct incomingProduct) {
        incomingProductRepository.save(incomingProduct);
    }

    public void updateIncomingProduct(IncomingProduct incomingProduct) {
        incomingProductRepository.save(incomingProduct);
    }

    public void deleteIncomingProduct(int id) {
        incomingProductRepository.deleteById(id);
    }
}

