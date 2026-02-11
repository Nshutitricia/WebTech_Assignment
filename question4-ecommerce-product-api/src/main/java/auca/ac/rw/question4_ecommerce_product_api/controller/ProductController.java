package auca.ac.rw.question4_ecommerce_product_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.question4_ecommerce_product_api.model.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "iPhone 15", "Latest Apple smartphone", 999.99, "Electronics", 50, "Apple"));
        products.add(new Product(2L, "Galaxy S24", "Samsung flagship phone", 899.99, "Electronics", 45, "Samsung"));
        products.add(new Product(3L, "MacBook Air", "Lightweight laptop M2", 1199.00, "Computers", 20, "Apple"));
        products.add(new Product(4L, "Dell XPS 13", "High performance laptop", 1050.50, "Computers", 15, "Dell"));
        products.add(new Product(5L, "Sony WH-1000XM5", "Noise cancelling headphones", 349.99, "Audio", 30, "Sony"));
        products.add(new Product(6L, "JBL Flip 6", "Portable bluetooth speaker", 129.99, "Audio", 0, "JBL"));
        products.add(new Product(7L, "Nike Air Max", "Running shoes", 120.00, "Clothing", 100, "Nike"));
        products.add(new Product(8L, "Adidas Hoodie", "Cotton casual hoodie", 65.00, "Clothing", 80, "Adidas"));
        products.add(new Product(9L, "PlayStation 5", "Gaming console", 499.99, "Gaming", 10, "Sony"));
        products.add(new Product(10L, "Logitech G502", "Gaming mouse", 49.99, "Gaming", 60, "Logitech"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand().equalsIgnoreCase(brand)) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(key) || p.getDescription().toLowerCase().contains(key)) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
  
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getProductsInStock() {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getStockQuantity() > 0) {
                result.add(p);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product details) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setName(details.getName());
                p.setDescription(details.getDescription());
                p.setPrice(details.getPrice());
                p.setCategory(details.getCategory());
                p.setBrand(details.getBrand());
                p.setStockQuantity(details.getStockQuantity());
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                p.setStockQuantity(quantity);
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        Product toRemove = null;
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                toRemove = p;
                break;
            }
        }
        
        if (toRemove != null) {
            products.remove(toRemove);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}