package org.Marias.BeautyAgenda.controller;


import org.Marias.BeautyAgenda.dto.PlantillaDTO;
import org.Marias.BeautyAgenda.dto.PlantillaRequestDTO;
import org.Marias.BeautyAgenda.service.PlantillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Plantillas")
public class PlantillaController {
    @Autowired
    private PlantillaService plantillaService;

    @GetMapping
    public List<PlantillaDTO> getAll(){
        return plantillaService.getAll();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<PlantillaDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(plantillaService.getById(id));

    }
    @PostMapping
    public ResponseEntity<PlantillaDTO> save(@RequestBody PlantillaRequestDTO dto){
        return ResponseEntity.ok(plantillaService.save(dto));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<PlantillaDTO> update(@PathVariable Long id, @RequestBody PlantillaRequestDTO dto){
        return ResponseEntity.ok(plantillaService.update(dto,id));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        plantillaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
