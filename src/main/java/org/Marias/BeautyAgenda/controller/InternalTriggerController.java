package org.Marias.BeautyAgenda.controller;

import org.Marias.BeautyAgenda.service.MensajeSchedulerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
public class InternalTriggerController {

    @Value("${app.internal.api-key}")
    private String apiKeyEsperada;

    private final MensajeSchedulerService schedulerService;

    public InternalTriggerController(MensajeSchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    private boolean claveValida(String claveRecibida) {
        return apiKeyEsperada != null && apiKeyEsperada.equals(claveRecibida);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("despierto");
    }

    @PostMapping("/enviar-mensajes-del-dia")
    public ResponseEntity<String> enviarMensajesDelDia(@RequestHeader("X-Internal-Key") String clave) {
        if (!claveValida(clave)) return ResponseEntity.status(401).build();
        schedulerService.enviarMensajesDelDia();
        return ResponseEntity.ok("procesado");
    }

    @PostMapping("/reintentar-fallidos")
    public ResponseEntity<String> reintentarFallidos(@RequestHeader("X-Internal-Key") String clave) {
        if (!claveValida(clave)) return ResponseEntity.status(401).build();
        schedulerService.reintentarFallidos();
        return ResponseEntity.ok("procesado");
    }

    @PostMapping("/generar-cumpleanos")
    public ResponseEntity<String> generarCumpleanos(@RequestHeader("X-Internal-Key") String clave) {
        if (!claveValida(clave)) return ResponseEntity.status(401).build();
        schedulerService.generarMensajesDeCumpleanos();
        return ResponseEntity.ok("procesado");
    }
}