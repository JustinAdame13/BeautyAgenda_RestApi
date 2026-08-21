package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.ClientaDTO;
import org.Marias.BeautyAgenda.service.ClientaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Clientas")
public class ClientaController {

    @Autowired
    private ClientaService clientaService;

    @GetMapping
    public List<ClientaDTO> findAll(){
        return clientaService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClientaDTO> findById(@PathVariable Long id){
        return clientaService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ClientaDTO> findByNombre(@PathVariable String nombre){
        return clientaService.findByNombre(nombre).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




}
