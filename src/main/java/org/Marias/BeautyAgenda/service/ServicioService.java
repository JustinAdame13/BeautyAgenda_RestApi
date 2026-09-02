package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.ServicioMapper;
import org.Marias.BeautyAgenda.dto.PlantillaRequestDTO;
import org.Marias.BeautyAgenda.dto.ServicioDTO;
import org.Marias.BeautyAgenda.dto.ServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.PlantillaMensaje;
import org.Marias.BeautyAgenda.entity.Servicio;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.PlantillaRepository;
import org.Marias.BeautyAgenda.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepo;

    @Autowired
    private PlantillaRepository plantillaRepo;

    //metodo para convertir una lista de ids de plantillas en un set de plantillas
    private Set<PlantillaMensaje> ListToSet (List<Long> idsPlantillas) {
         Set<PlantillaMensaje> plantillas = plantillaRepo.findAllById(idsPlantillas).stream().collect(Collectors.toSet());

         if (idsPlantillas.size() != plantillas.size()) {
             throw new EntidadNoEncontradaException("alguna plantilla no fue encontrada o fue repetida");
         }
         return plantillas;
    }

    //metodo para buscar todos los servicios
    @Transactional(readOnly = true)
    public List<ServicioDTO> findAll(){
        return servicioRepo.findAll().stream()
                .map(ServicioMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar por nombre
    @Transactional(readOnly = true)
    public List<ServicioDTO> findByNombre(String nombre){
        return servicioRepo.findByNombreContainingIgnoreCase(nombre).stream()
                .map(ServicioMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar por id
    @Transactional(readOnly = true)
    public ServicioDTO findById(Long id){
        Servicio servicio = servicioRepo.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Servicio no encontrado"));

        return ServicioMapper.toDTO(servicio);
    }
    //metodo para guardar servicio nueva
    @Transactional
    public ServicioDTO save(ServicioRequestDTO dto){
        Set<PlantillaMensaje> plantillas = ListToSet(dto.getIdsPlantillas());
        Servicio servicio = ServicioMapper.RqToEntity(dto,plantillas);
        return ServicioMapper.toDTO(servicioRepo.save(servicio));
    }
    //metodo para actualizar servicio
    @Transactional
    public ServicioDTO update(ServicioRequestDTO dto, Long id){
        Optional<Servicio> existe = servicioRepo.findById(id);
        if(existe.isPresent()){
            Servicio servicio = existe.get();
            servicio.setNombre(dto.getNombre());
            servicio.setDuracion(dto.getDuracion());
            servicio.setPrecio(dto.getPrecio());
            servicio.setTipo(dto.getTipo());
            servicio.setDescripcion(dto.getDescripcion());

            Set<PlantillaMensaje> plantillas = ListToSet(dto.getIdsPlantillas());
            servicio.getPlantillas().clear();
            servicio.getPlantillas().addAll(plantillas);

            return ServicioMapper.toDTO(servicioRepo.save(servicio));
        }
        else {
            throw new EntidadNoEncontradaException("Servicio no encontrado");
        }
    }
    //metodo para borrar
    public void delete(Long id){
        if(servicioRepo.findById(id).isPresent()){
            servicioRepo.deleteById(id);
        }
        else {
            throw new EntidadNoEncontradaException("Servicio no encontrado");
        }
    }
}
