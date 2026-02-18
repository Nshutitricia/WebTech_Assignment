package auca.ac.rw.restfullApiAssignment.service;

import java.util.List; // Required for listAllProducts
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.restfullApiAssignment.modal.Product;
import auca.ac.rw.restfullApiAssignment.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo; 

    public String saveProduct(Product product){
        Optional<Product> checkProduct = productRepo.findById(product.getId());
        if(checkProduct.isPresent()){
            return "Product with id "+ product.getId() + " already exists.";
        } else {
            productRepo.save(product);
            return "Product saved successfully.";
        }
    }

    public List<Product> listAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }


    public String updateProduct(Long id, Product details) {
        return productRepo.findById(id).map(existingProduct -> {
            existingProduct.setName(details.getName());
            existingProduct.setDescription(details.getDescription());
            existingProduct.setPrice(details.getPrice());
            existingProduct.setCategory(details.getCategory());
            existingProduct.setStockQuantity(details.getStockQuantity());
            productRepo.save(existingProduct);
            return "Product updated successfully.";
        }).orElse("Product with id " + id + " not found.");
    }

    public String deleteProduct(Long id) {
        if(productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Product deleted successfully.";
        }
        return "Product with id " + id + " not found.";
    }
}