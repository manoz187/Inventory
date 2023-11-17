package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Items;

public interface ItemRepository extends JpaRepository<Items, Long> {

    

    List<Items> findByNameContaining(String name);
    
}
