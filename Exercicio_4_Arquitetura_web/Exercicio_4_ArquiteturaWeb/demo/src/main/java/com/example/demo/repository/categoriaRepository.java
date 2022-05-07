package com.example.demo.repository;


import com.example.demo.entity.Categoria;

//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.transaction.Transactional;



//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;



public interface categoriaRepository extends JpaRepository<Categoria,Integer> {

}
