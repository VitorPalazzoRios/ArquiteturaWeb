package com.example.odsparatodos.controllers;

import java.util.List;

import com.example.odsparatodos.Repository.ODSRepository;
import com.example.odsparatodos.entity.ODS;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/ODS")
public class ODSController {
    private ODSRepository odsRepository;

    public ODSController(ODSRepository odsRepository){
        this.odsRepository = odsRepository;
    }

    @GetMapping("/{id}")    
    public ODS getById(@PathVariable int id){
        return odsRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "ODS não encontrada."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ODS save(@RequestBody ODS ods) {
        return odsRepository.save(ods);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable int id){
        odsRepository.findById(id)
            .map(ODS -> {
                odsRepository.delete(ODS);
                return ODS;
            })
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"ODS não encontrada"));        
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable int id,@RequestBody ODS ODS){
                                            odsRepository.findById(id)
                .map(ProjetoExistente ->{
                    ODS.setId(ProjetoExistente.getId());
                    odsRepository.save(ODS);
                    return ProjetoExistente;
                }) 
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"ODS não encontrado.")); 
    }

    @GetMapping
    public List<ODS> find(ODS filtro){
        ExampleMatcher matcher = ExampleMatcher.matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                        StringMatcher.CONTAINING);

        Example example = (Example) Example.of(filtro,matcher);
        return odsRepository.findAll(example);
    }
    
    
    @RequestMapping(value = {"/teste/{nome}","/teste2/{nome}"}, 
                    method = RequestMethod.GET,
                    consumes = {"application/json","application/xml"},
                    produces = {"application/json","application/xml"})
    public String testePerfil(@PathVariable("nome") String nomeODS){
        return String.format("Olá %s",nomeODS);
    }
    
}
