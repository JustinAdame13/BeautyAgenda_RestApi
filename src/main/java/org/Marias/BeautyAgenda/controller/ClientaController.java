package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.ClientaDTO;
import org.Marias.BeautyAgenda.dto.ClientaRequestDTO;
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
        return ResponseEntity.ok(clientaService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    public List<ClientaDTO> findByNombre(@PathVariable String nombre){
        return clientaService.findByNombre(nombre);
    }

    @PostMapping("/post")
    public ResponseEntity<ClientaDTO> save(@RequestBody ClientaRequestDTO dto){
        return ResponseEntity.ok(clientaService.save(dto));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ClientaDTO> update(@PathVariable Long id, @RequestBody ClientaRequestDTO dto){
        return ResponseEntity.ok(clientaService.update(id,dto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientaService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
