package com.example.demo.controllers;
import java.util.List;

import com.example.demo.entity.Categoria;
import com.example.demo.repository.categoriaRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/categoria")
public class categoriaController {
    private categoriaRepository categoriaRepository1;

    public categoriaController(categoriaRepository categoriaRepository1){
        this.categoriaRepository1 = categoriaRepository1;
    }

    @GetMapping("/{id}")    
    public Categoria getById(@PathVariable int id){
        return categoriaRepository1.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Categoria não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria save(@RequestBody Categoria categoria) {
        return categoriaRepository1.save(categoria);  
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        categoriaRepository1.findById(id)
            .map(categoria -> {
                categoriaRepository1.delete(categoria);
                return categoria;
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Categoria não encontrado"));        
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable int id, 
                                        @RequestBody Categoria categoria){
         categoriaRepository1.findById(id)
                .map(categoriaExistente ->{
                    categoria.setId(categoriaExistente.getId());
                    categoriaRepository1.save(categoria);
                    return categoriaExistente;
                }) 
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Categoria não encontrado.")); 
    }
    @GetMapping
    public List<Categoria> find(Categoria filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                        StringMatcher.CONTAINING);

        Example example = Example.of(filtro,matcher);
        return categoriaRepository1.findAll(example);
    }
    
    
    @RequestMapping(value = {"/teste/{nome}","/teste2/{nome}"}, 
                    method = RequestMethod.GET,
                    consumes = {"application/json","application/xml"},
                    produces = {"application/json","application/xml"})
    public String testeCategoria(@PathVariable("nome") String nomeCategoria){
        return String.format("Olá %s",nomeCategoria );
    }
}