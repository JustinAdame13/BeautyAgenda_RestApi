package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.MensajeDTO;
import org.Marias.BeautyAgenda.dto.MensajeUpdateDTO;
import org.Marias.BeautyAgenda.entity.enums.EstadoMensaje;
import org.Marias.BeautyAgenda.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Mensajes")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public List<MensajeDTO> findAll(){
        return mensajeService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MensajeDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(mensajeService.findById(id));
    }

    @GetMapping("/Estado/{estado}")
    public List<MensajeDTO> findByEstado(@PathVariable EstadoMensaje estado) {

        return mensajeService.findByEstado(estado);

    }
    @GetMapping("/ClientaId/{id}")
    public List<MensajeDTO> findByClienta(@PathVariable Long id){
        return mensajeService.findByClienta(id);
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<MensajeDTO> update(@PathVariable Long id, @RequestBody MensajeUpdateDTO dto){
        return ResponseEntity.ok(mensajeService.update(dto,id));
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        mensajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
