package com.hasansajjadswati.CRUDProduct;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name){
        return productService.getAllProducts(name);
    }
    
    @PostMapping("/product")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
        return productService.createNewProduct(product);
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id,@RequestBody Product product){
    	return productService.updateProduct(id, product);
    }
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer id) {
        return productService.deleteProduct(id);
    }
    
    @DeleteMapping("/product")
    public ResponseEntity<HttpStatus> deleteAllTProducts() {
        return productService.deleteAllProducts();
    }
}
