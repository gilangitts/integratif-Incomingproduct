package com.example.swalayan.controller;

import com.example.swalayan.model.IncomingProduct;
import com.example.swalayan.model.IncomingProductDTO;
import com.example.swalayan.repository.IncomingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incoming-products")
public class IncomingProductController {

    private final IncomingProductService incomingProductService;

    @Autowired
    public IncomingProductController(IncomingProductService incomingProductService) {
        this.incomingProductService = incomingProductService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<IncomingProductDTO>> getAllIncomingProducts() {
        List<IncomingProductDTO> incomingProducts = incomingProductService.getAllIncomingProducts();
        return new ResponseEntity<>(incomingProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomingProductDTO>getIncomingProductById(@PathVariable Long id) {
        IncomingProductDTO incomingProduct = incomingProductService.getIncomingProductById(id);
        if (incomingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(incomingProduct);
//        if (incomingProduct != null) {
//            return new ResponseEntity<>(incomingProduct, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<IncomingProductDTO> addIncomingProduct(@RequestBody IncomingProductDTO incomingProductDTO) {
        IncomingProductDTO createdIncomingProduct = incomingProductService.addIncomingProduct(incomingProductDTO);
        return new ResponseEntity<>(createdIncomingProduct, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<IncomingProduct> updateIncomingProduct(@PathVariable Long id, @RequestBody IncomingProduct updatedIncomingProduct) {
//        IncomingProduct existingIncomingProduct = incomingProductService.getIncomingProductById(id);
//        if (existingIncomingProduct != null) {
//            existingIncomingProduct.setQuantity(updatedIncomingProduct.getQuantity());
//            existingIncomingProduct.setDate(updatedIncomingProduct.getDate());
//            incomingProductService.updateIncomingProduct(existingIncomingProduct);
//            return new ResponseEntity<>(existingIncomingProduct, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
     @PutMapping("/{id}")
     public ResponseEntity<IncomingProductDTO> updateIncomingProduct(@PathVariable("id") Long id, @RequestBody IncomingProductDTO updatedIncomingProductDTO) {
         IncomingProductDTO existingIncomingProductDTO = incomingProductService.getIncomingProductById(id);
         if (existingIncomingProductDTO != null) {
             existingIncomingProductDTO.setQuantity(updatedIncomingProductDTO.getQuantity());
             existingIncomingProductDTO.setDate(updatedIncomingProductDTO.getDate());
             IncomingProductDTO updatedIncomingProduct = incomingProductService.updateIncomingProduct(existingIncomingProductDTO);
             if (updatedIncomingProduct != null) {
                 return new ResponseEntity<>(updatedIncomingProduct, HttpStatus.OK);
             } else {
                 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteIncomingProduct(@PathVariable long id) {
//        IncomingProduct existingIncomingProduct = incomingProductService.getIncomingProductById(id);
//        if (existingIncomingProduct != null) {
//            incomingProductService.deleteIncomingProduct(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteIncomingProduct(@PathVariable("id") Long id) {
        IncomingProductDTO existingIncomingProductDTO = incomingProductService.getIncomingProductById(id);
        if (existingIncomingProductDTO != null) {
            incomingProductService.deleteIncomingProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
}

