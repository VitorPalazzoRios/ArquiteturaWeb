package com.example.odsparatodos.controllers;

import java.util.List;

import com.example.odsparatodos.Repository.projetoRepository;
//import com.example.odsparatodos.entity.PessoaJuridica;
import com.example.odsparatodos.entity.Projeto;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/Projeto")
public class ProjetoController {
    private projetoRepository projetoRepository1;

    public ProjetoController(projetoRepository projetoRepository1){
        this.projetoRepository1 = projetoRepository1;
    }

    @GetMapping("/{id}")    
    public Projeto getById(@PathVariable int id){
        return projetoRepository1.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Projeto não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto save(@RequestBody Projeto projeto) {
        return projetoRepository1.save(projeto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        projetoRepository1.findById(id)
            .map(Projeto -> {
                projetoRepository1.delete(Projeto);
                return Projeto;
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Projeto não encontrado"));        
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable int id,@RequestBody Projeto projeto){
                                            projetoRepository1.findById(id)
                .map(ProjetoExistente ->{
                    projeto.setId(ProjetoExistente.getId());
                    projetoRepository1.save(projeto);
                    return ProjetoExistente;
                }) 
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Projeto não encontrado.")); 
    }

    @GetMapping
    public List<Projeto> find(Projeto filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                        StringMatcher.CONTAINING);

        Example example = (Example) Example.of(filtro,matcher);
        return projetoRepository1.findAll(example);
    }
    
    
    @RequestMapping(value = {"/teste/{nome}","/teste2/{nome}"}, 
                    method = RequestMethod.GET,
                    consumes = {"application/json","application/xml"},
                    produces = {"application/json","application/xml"})
    public String testePerfil(@PathVariable("nome") String nomeProjeto){
        return String.format("Olá %s",nomeProjeto);
    }

}
