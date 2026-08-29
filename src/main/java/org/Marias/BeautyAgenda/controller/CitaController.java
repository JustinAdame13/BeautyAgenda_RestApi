package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.CitaDTO;
import org.Marias.BeautyAgenda.dto.CitaRequestDTO;
import org.Marias.BeautyAgenda.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<CitaDTO> getAll(){
        return citaService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CitaDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(citaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CitaDTO> save(@RequestBody CitaRequestDTO dto){
        return ResponseEntity.ok(citaService.save(dto));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @RequestBody CitaRequestDTO dto){
        return ResponseEntity.ok(citaService.update(id, dto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        citaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
