package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.CitaDTO;
import org.Marias.BeautyAgenda.dto.CitaRequestDTO;
import org.Marias.BeautyAgenda.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/Citas")
public class CitaController {

    private static final ZoneId ZONA_NEGOCIO = ZoneId.of("America/Mexico_City");

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
        System.out.println("[CONTROLLER] Recibiendo cita del frontend:");
        System.out.println("[CONTROLLER] inicio: " + dto.getInicio());
        System.out.println("[CONTROLLER] fin: " + dto.getFin());
        System.out.println("[CONTROLLER] Zona horaria negocio: " + ZONA_NEGOCIO);
        System.out.println("[CONTROLLER] Hora actual en zona negocio: " + ZonedDateTime.now(ZONA_NEGOCIO));

        return ResponseEntity.ok(citaService.save(dto));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @RequestBody CitaRequestDTO dto){
        System.out.println("[CONTROLLER] Actualizando cita del frontend:");
        System.out.println("[CONTROLLER] inicio: " + dto.getInicio());
        System.out.println("[CONTROLLER] fin: " + dto.getFin());

        return ResponseEntity.ok(citaService.update(id, dto));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        citaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
