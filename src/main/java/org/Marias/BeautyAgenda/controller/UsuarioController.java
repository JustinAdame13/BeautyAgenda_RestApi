package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.dto.UsuarioDTO;
import org.Marias.BeautyAgenda.dto.UsuarioRequestDTO;
import org.Marias.BeautyAgenda.entity.Usuario;
import org.Marias.BeautyAgenda.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> findAll(){
        return usuarioService.findAll();
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(usuarioService.save(dto));
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(usuarioService.update(dto, id));
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

}
