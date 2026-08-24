package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.ServicioMapper;
import org.Marias.BeautyAgenda.dto.ServicioDTO;
import org.Marias.BeautyAgenda.dto.ServicioRequestDTO;
import org.Marias.BeautyAgenda.entity.Servicio;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepo;
    //metodo para buscar todos los servicios
    public List<ServicioDTO> findAll(){
        return servicioRepo.findAll().stream()
                .map(ServicioMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar por nombre
    public List<ServicioDTO> findByNombre(String nombre){
        return servicioRepo.findByNombreContainingIgnoreCase(nombre).stream()
                .map(ServicioMapper::toDTO).collect(Collectors.toList());
    }
    //metodo para buscar por id
    public ServicioDTO findById(Long id){
        Servicio servicio = servicioRepo.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Servicio no encontrado"));

        return ServicioMapper.toDTO(servicio);
    }
    //metodo para guardar clienta nueva
    public ServicioDTO save(ServicioRequestDTO dto){
        Servicio servicio = ServicioMapper.RqToEntity(dto);
        return ServicioMapper.toDTO(servicioRepo.save(servicio));
    }
    //metodo para actualizar clienta
    public ServicioDTO update(ServicioRequestDTO dto, Long id){
        Optional<Servicio> existe = servicioRepo.findById(id);
        if(existe.isPresent()){
            Servicio servicio = existe.get();
            servicio.setNombre(dto.getNombre());
            servicio.setDuracion(dto.getDuracion());
            servicio.setPrecio(dto.getPrecio());
            servicio.setTipo(dto.getTipo());
            servicio.setDescripcion(dto.getDescripcion());
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
