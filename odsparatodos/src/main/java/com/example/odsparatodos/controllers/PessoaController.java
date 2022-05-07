package com.example.odsparatodos.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.odsparatodos.Repository.pessoaRepository;
//import com.example.odsparatodos.entity.PessoaJuridica;
import com.example.odsparatodos.entity.Pessoa;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaController {
    private pessoaRepository pessoaRepository1;

    public PessoaController(pessoaRepository pessoaRepository1){
        this.pessoaRepository1 = pessoaRepository1;
    }

    @GetMapping("/{id}")    
    public Pessoa getById(@PathVariable int id){
        return pessoaRepository1.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Pessoa não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa save(@Valid@RequestBody Pessoa Pessoa) {
        return pessoaRepository1.save(Pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        pessoaRepository1.findById(id)
            .map(Pessoa -> {
                pessoaRepository1.delete(Pessoa);
                return Pessoa;
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Pessoa não encontrado"));        
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable int id,@RequestBody Pessoa Pessoa){
                                            pessoaRepository1.findById(id)
                .map(ProjetoExistente ->{
                    Pessoa.setId(ProjetoExistente.getId());
                    pessoaRepository1.save(Pessoa);
                    return ProjetoExistente;
                }) 
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Pessoa não encontrado.")); 
    }

    @GetMapping
    public List<Pessoa> find(Pessoa filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                        StringMatcher.CONTAINING);

        Example example = (Example) Example.of(filtro,matcher);
        return pessoaRepository1.findAll(example);
    }
    
    
    @RequestMapping(value = {"/teste/{nome}","/teste2/{nome}"}, 
                    method = RequestMethod.GET,
                    consumes = {"application/json","application/xml"},
                    produces = {"application/json","application/xml"})
    public String testePerfil(@PathVariable("nome") String nomePessoa){
        return String.format("Olá %s",nomePessoa);
    }

}
