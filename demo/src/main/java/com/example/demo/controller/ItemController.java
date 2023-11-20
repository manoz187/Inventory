package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ItemRepository;
import com.example.demo.model.Items;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Items>> getAllitems(@RequestParam(required = false) String name){
        try{
            List<Items> items =  new ArrayList<Items>();
            if(name == null){
                itemRepository.findAll().forEach(items::add);
            }
            else
                itemRepository.findByNameContaining(name).forEach(items::add);
                
            if(items.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(items,HttpStatus.OK);


         

        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/items/{id}")
    public ResponseEntity<Items> getItembyId(@PathVariable("id")long id){
        Optional<Items> itemdata = itemRepository.findById(id);

        if(itemdata.isPresent()){
            return new ResponseEntity<>(itemdata.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/items")
    public ResponseEntity<Items> createItem(@RequestBody Items item){
        try{
            Items _item = itemRepository.save(new Items(item.getId(),item.getName(),item.getCategory(),item.getExpiry()));
            return new ResponseEntity<>(_item,HttpStatus.CREATED);

        } catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") long id){
        try{
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
