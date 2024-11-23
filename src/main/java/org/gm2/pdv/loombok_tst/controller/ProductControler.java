package org.gm2.pdv.loombok_tst.controller;

import org.gm2.pdv.loombok_tst.entity.Product;
import org.gm2.pdv.loombok_tst.entity.User;
import org.gm2.pdv.loombok_tst.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductControler {

    private ProductRepository productRepository;

    public ProductControler(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public ResponseEntity getAll(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity post(@RequestBody Product product ){
        try{
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
        }catch(Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity put(@RequestBody Product product){
        try{
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            productRepository.deleteById(id);
            return new ResponseEntity<>("Product Removed", HttpStatus.OK);
        }catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
