
package com.hasansajjadswati.CRUDProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public ResponseEntity<List<Product>> getAllProducts(String name){
        
    	try {
    		List<Product> products = new ArrayList<>();
    		
    		if(name == null)
    			productRepository.findAll().forEach(products::add);
    		
    		else
    			productRepository.findByName(name).forEach(products::add);
    		
    		if(products.isEmpty())
    			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    		
    		return new ResponseEntity<>(products,HttpStatus.OK);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    }
    
    public ResponseEntity<Product> getProductById(Integer id){
    	Optional<Product> productDetails = productRepository.findById(id);
    	
    	if(productDetails.isPresent())
    		return new ResponseEntity<>(productDetails.get(),HttpStatus.OK);
    	
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity<Product> createNewProduct(Product product){
    	
    	try {
    		Product newProduct = productRepository.save(new Product(product.getName(),product.getPrice()));
    		
    		return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    public ResponseEntity<Product> updateProduct(Integer id,Product product){
    	Optional<Product> productDetails = productRepository.findById(id);
    	
    	if(productDetails.isPresent()) {
    		Product updateProduct = productDetails.get();
    		updateProduct.setName(product.getName());
    		updateProduct.setPrice(product.getPrice());
    		
    		return new ResponseEntity<>(productRepository.save(updateProduct),HttpStatus.OK);
    		
    	}
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity<HttpStatus> deleteProduct(Integer id) {
      try {
        productRepository.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } 
      catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    public ResponseEntity<HttpStatus> deleteAllProducts() {
      try {
        productRepository.deleteAll();
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } 
      catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
    
}
