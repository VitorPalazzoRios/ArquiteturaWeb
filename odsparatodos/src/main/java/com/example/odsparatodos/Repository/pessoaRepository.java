package com.example.odsparatodos.Repository;

import com.example.odsparatodos.entity.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface pessoaRepository extends JpaRepository<Pessoa,Integer> {
 
}