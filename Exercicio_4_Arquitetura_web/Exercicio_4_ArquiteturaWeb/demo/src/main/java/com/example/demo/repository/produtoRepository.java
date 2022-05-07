package com.example.demo.repository;


import com.example.demo.entity.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface produtoRepository extends JpaRepository<Produto,Integer>{
}
