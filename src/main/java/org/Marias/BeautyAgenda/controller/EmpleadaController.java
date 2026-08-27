package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.EmpleadaDTO;
import org.Marias.BeautyAgenda.dto.EmpleadaRequestDTO;
import org.Marias.BeautyAgenda.entity.Empleada;
import org.Marias.BeautyAgenda.service.EmpleadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Empleadas")
public class EmpleadaController {
    @Autowired
    private EmpleadaService empleadaService;

    @GetMapping
    public List<EmpleadaDTO> getEmpleadas() {
        return empleadaService.findAll();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<EmpleadaDTO> getEmpleadasById(@PathVariable Long id) {
        return ResponseEntity.ok(empleadaService.findbyId(id));
    }
    @PostMapping
    public ResponseEntity<EmpleadaDTO> save (EmpleadaRequestDTO dto){
        return ResponseEntity.ok(empleadaService.save(dto));
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<EmpleadaDTO> update (@RequestBody EmpleadaRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(empleadaService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        empleadaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
