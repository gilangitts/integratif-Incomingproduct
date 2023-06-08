package com.example.swalayan.controller;

import com.example.swalayan.model.IncomingProduct;
import com.example.swalayan.repository.IncomingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incoming-products")
public class IncomingProductController {
    @Autowired
    private IncomingProductService incomingProductService;

    @GetMapping
    public ResponseEntity<List<IncomingProduct>> getAllIncomingProducts() {
        List<IncomingProduct> incomingProducts = incomingProductService.getAllIncomingProducts();
        return new ResponseEntity<>(incomingProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomingProduct> getIncomingProductById(@PathVariable int id) {
        IncomingProduct incomingProduct = incomingProductService.getIncomingProductById(id);
        if (incomingProduct != null) {
            return new ResponseEntity<>(incomingProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<IncomingProduct> addIncomingProduct(@RequestBody IncomingProduct incomingProduct) {
        incomingProductService.addIncomingProduct(incomingProduct);
        return new ResponseEntity<>(incomingProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomingProduct> updateIncomingProduct(@PathVariable int id, @RequestBody IncomingProduct updatedIncomingProduct) {
        IncomingProduct existingIncomingProduct = incomingProductService.getIncomingProductById(id);
        if (existingIncomingProduct != null) {
            existingIncomingProduct.setQuantity(updatedIncomingProduct.getQuantity());
            existingIncomingProduct.setDate(updatedIncomingProduct.getDate());
            incomingProductService.updateIncomingProduct(existingIncomingProduct);
            return new ResponseEntity<>(existingIncomingProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncomingProduct(@PathVariable int id) {
        IncomingProduct existingIncomingProduct = incomingProductService.getIncomingProductById(id);
        if (existingIncomingProduct != null) {
            incomingProductService.deleteIncomingProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

