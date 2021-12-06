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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @RequestMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name){

		
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
    
    @RequestMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id){
    	Optional<Product> productDetails = productRepository.findById(id);
    	
    	if(productDetails.isPresent())
    		return new ResponseEntity<>(productDetails.get(),HttpStatus.OK);
    	
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/product")
    public ResponseEntity<Product> createNewProduct(@RequestBody Product product){
    	
    	try {
    		Product newProduct = productRepository.save(new Product(product.getName(),product.getPrice()));
    		
    		return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
    	}
    	catch(Exception e) {
    		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id,@RequestBody Product product){
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
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Integer id) {
      try {
        productRepository.deleteById(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } 
      catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    @DeleteMapping("/product")
    public ResponseEntity<HttpStatus> deleteAllTProducts() {
      try {
        productRepository.deleteAll();
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } 
      catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}
