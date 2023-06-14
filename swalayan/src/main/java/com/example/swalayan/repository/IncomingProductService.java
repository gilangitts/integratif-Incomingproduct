package com.example.swalayan.repository;

import com.example.swalayan.model.IncomingProduct;
import com.example.swalayan.model.IncomingProductDTO;
import com.example.swalayan.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomingProductService {

    @Autowired
    private final IncomingProductRepository incomingProductRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IncomingProductService(IncomingProductRepository incomingProductRepository, ModelMapper modelMapper) {
        this.incomingProductRepository = incomingProductRepository;
        this.modelMapper = modelMapper;

    }
    public List<IncomingProductDTO> getAllIncomingProducts() {
        List<IncomingProduct> incomingProducts = (List<IncomingProduct>) incomingProductRepository.findAll();
        return incomingProducts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public IncomingProductDTO getIncomingProductById(Long id_product) {
        IncomingProduct transaction = incomingProductRepository.findById(id_product)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id_product));
        return convertToDTO(transaction);
    }

    public IncomingProductDTO addIncomingProduct(IncomingProductDTO incomingProductDTO) {
        IncomingProduct incomingProduct = convertToEntity(incomingProductDTO);
        IncomingProduct createdIncomingProduct = incomingProductRepository.save(incomingProduct);
        return convertToDTO(createdIncomingProduct);
    }

//    public void updateIncomingProduct(IncomingProduct incomingProduct) {
//        incomingProductRepository.save(incomingProduct);
//    }
     public IncomingProductDTO updateIncomingProduct(IncomingProductDTO incomingProductDTO) {
            Optional<IncomingProduct> optionalIncomingProduct = incomingProductRepository.findById(incomingProductDTO.getId());
            if (optionalIncomingProduct.isPresent()) {
                IncomingProduct existingIncomingProduct = optionalIncomingProduct.get();
                existingIncomingProduct.setQuantity(incomingProductDTO.getQuantity());
                existingIncomingProduct.setDate(incomingProductDTO.getDate());
                IncomingProduct updatedIncomingProduct = incomingProductRepository.save(existingIncomingProduct);
                return convertToDTO(updatedIncomingProduct);
            }
            return null;
        }

    public void deleteIncomingProduct(Long id) {
        incomingProductRepository.deleteById(id);
    }

//modelmapper
    private IncomingProductDTO convertToDTO(IncomingProduct incomingProduct) {
        IncomingProductDTO incomingProductDTO = modelMapper.map(incomingProduct, IncomingProductDTO.class);
        incomingProductDTO.setId_product(incomingProduct.getId_product().getId());

        return incomingProductDTO;
    }
//model mappper to entity
    private IncomingProduct convertToEntity(IncomingProductDTO incomingProductDTO) {
        IncomingProduct incomingProduct = modelMapper.map(incomingProductDTO, IncomingProduct.class);

        Product product = new Product();
        product.setId(incomingProductDTO.getId_product());
//        Product product = getProductById(transactionItemDTO.getId_product());

        incomingProduct.setId_product(product);
        return incomingProduct;
    }
//resttemplate

}

