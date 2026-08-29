package org.Marias.BeautyAgenda.service;

import jakarta.transaction.Transactional;
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


import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public List<CitaDTO> findAll(){
        return citaRepo.findAll().stream().map(CitaMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar cita por id
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
    @Transactional //si algo falla_todo se devuelve
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

        cita.getCitaServicio().clear();
        cita.getCitaServicio().addAll(dto.getServicios().stream()
                .map(dtoServicio -> CitaServicioMapper.RqtoEntity(
                        dtoServicio, cita, servicios.get(dtoServicio.getIdServicio())))
                .collect(Collectors.toList()));

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
