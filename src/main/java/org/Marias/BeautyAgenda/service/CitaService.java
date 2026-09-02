package org.Marias.BeautyAgenda.service;


import org.Marias.BeautyAgenda.Mapper.CitaMapper;
import org.Marias.BeautyAgenda.Mapper.CitaServicioMapper;
import org.Marias.BeautyAgenda.dto.CitaDTO;
import org.Marias.BeautyAgenda.dto.CitaRequestDTO;

import org.Marias.BeautyAgenda.dto.CitaServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.*;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepo;
    @Autowired
    private CitaServicioRepository citaServicioRepo;
    @Autowired
    private ClientaRepository clientaRepo;
    @Autowired
    private EmpleadaRepository empleadaRepo;
    @Autowired
    private ServicioRepository servicioRepo;

    //metodo utilitario que convierte una lista CitaServicioRequestDTO a map<Long, servicio>
    private Map<Long, Servicio> resolverServicios(List<CitaServicioRequestDTO> serviciosDTO) {
        List<Long> idsServicios = serviciosDTO.stream()
                .map(CitaServicioRequestDTO::getIdServicio)
                .toList();

        Map<Long, Servicio> servicios = servicioRepo.findAllById(idsServicios)
                .stream().collect(Collectors.toMap(Servicio::getId, s -> s));

        if (servicios.size() != idsServicios.size()) {
            throw new EntidadNoEncontradaException("Uno o más servicios no fueron encontrados");
        }

        return servicios;
    }

    //metodo para encontar todas las citas
    @Transactional(readOnly = true)
    public List<CitaDTO> findAll(){
        return citaRepo.findAll().stream().map(CitaMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar cita por id
    @Transactional(readOnly = true)
    public CitaDTO findById(Long id){
        Cita cita = citaRepo.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Cita no encontrada"));
        return CitaMapper.toDTO(cita);
    }
    //metodo para guardar citas
    @Transactional //si algo falla_todo se devuelve
    public CitaDTO save(CitaRequestDTO dto){
        Clienta clienta = clientaRepo.findById(dto.getIdClienta())
                .orElseThrow(()-> new EntidadNoEncontradaException("Clienta no encontrada"));
        Empleada empleada = empleadaRepo.findById(dto.getIdEmpleada())
                .orElseThrow(()-> new EntidadNoEncontradaException("Empleada no encontrada"));

        Map<Long, Servicio> servicios = resolverServicios(dto.getServicios());

        Cita cita = CitaMapper.RqToEntity(dto, clienta, empleada, servicios);
        return CitaMapper.toDTO(citaRepo.save(cita));
    }
  
    //metodo para editar citas
    @Transactional
    public CitaDTO update(Long id, CitaRequestDTO dto){
        Cita cita = citaRepo.findById(id)
                .orElseThrow(()-> new EntidadNoEncontradaException("Cita no encontrada"));

        cita.setClienta(clientaRepo.findById(dto.getIdClienta())
                .orElseThrow(()-> new EntidadNoEncontradaException("Clienta no encontrada")));
        cita.setEmpleada(empleadaRepo.findById(dto.getIdEmpleada())
                .orElseThrow(()-> new EntidadNoEncontradaException("Empleada no encontrada")));
        cita.setInicio(dto.getInicio());
        cita.setFin(dto.getFin());
        cita.setEstado(dto.getEstado());
        cita.setNotas(dto.getNotas());

        Map<Long, Servicio> servicios = resolverServicios(dto.getServicios());

        // Indexamos la colección actual por idServicio para poder buscarla en O(1)
        Map<Long, CitaServicio> actuales = cita.getCitaServicio().stream()
                .collect(Collectors.toMap(cs -> cs.getServicio().getId(), cs -> cs));

        // Set para saber, al final, qué idServicio siguen vigentes según el DTO
        Set<Long> idsEnDto = dto.getServicios().stream()
                .map(CitaServicioRequestDTO::getIdServicio)
                .collect(Collectors.toSet());

        // 1. Actualizar existentes o agregar nuevos
        for (CitaServicioRequestDTO servicioDto : dto.getServicios()) {
            CitaServicio existente = actuales.get(servicioDto.getIdServicio());

            if (existente != null) {
                // Ya existía esa combinación cita-servicio: solo actualizamos el precio
                existente.setPrecioCobrado(servicioDto.getPrecioCobrado());
            } else {
                // No existía: lo agregamos como nuevo
                CitaServicio nuevo = CitaServicioMapper.RqtoEntity(
                        servicioDto, cita, servicios.get(servicioDto.getIdServicio()));
                cita.getCitaServicio().add(nuevo);
            }
        }

        // 2. Eliminar los que ya no vienen en el DTO (orphanRemoval se encarga del DELETE)
        cita.getCitaServicio().removeIf(cs -> !idsEnDto.contains(cs.getServicio().getId()));

        return CitaMapper.toDTO(citaRepo.save(cita));
    }
    //metodo para borrar
    public void delete(Long id){
        if(citaRepo.existsById(id)){
            citaRepo.deleteById(id);
        }
        else{
            throw new EntidadNoEncontradaException("Cita no encontrada");
        }
    }

}
