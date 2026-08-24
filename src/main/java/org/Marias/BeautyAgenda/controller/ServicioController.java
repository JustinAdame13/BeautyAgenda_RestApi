package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.Mapper.ServicioMapper;
import org.Marias.BeautyAgenda.dto.ServicioDTO;
import org.Marias.BeautyAgenda.dto.ServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.Servicio;
import org.Marias.BeautyAgenda.service.ClientaService;
import org.Marias.BeautyAgenda.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;
    @Autowired
    private ClientaService clientaService;

    @GetMapping
    public List<ServicioDTO> getServicios(){
        return servicioService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ServicioDTO> getServicioById(@PathVariable Long id){
        return ResponseEntity.ok(servicioService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    public List<ServicioDTO> getServicioByNombre(@PathVariable String nombre){
        return servicioService.findByNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> createServicio(@RequestBody ServicioRequestDTO dto){
        return ResponseEntity.ok(servicioService.save(dto));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ServicioDTO> updateServicio(@RequestBody ServicioRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(servicioService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<ServicioDTO> deleteServicio(@PathVariable Long id){
        servicioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
