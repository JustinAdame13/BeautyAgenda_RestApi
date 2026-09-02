package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.PlantillaMapper;
import org.Marias.BeautyAgenda.dto.PlantillaDTO;
import org.Marias.BeautyAgenda.dto.PlantillaRequestDTO;
import org.Marias.BeautyAgenda.entity.PlantillaMensaje;
import org.Marias.BeautyAgenda.exception.EntidadNoEncontradaException;
import org.Marias.BeautyAgenda.repository.PlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantillaService {

    @Autowired
    private PlantillaRepository plantillaRepo;

    @Transactional(readOnly = true)
    public List<PlantillaDTO> getAll() {
        return plantillaRepo.findAll().stream().map(PlantillaMapper::toDTO).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public PlantillaDTO getById(Long id) {
        return PlantillaMapper.toDTO(plantillaRepo.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("No existe el plantilla con el id: " + id)));
    }

    @Transactional
    public PlantillaDTO save(PlantillaRequestDTO request){
        return PlantillaMapper.toDTO(plantillaRepo.save(PlantillaMapper.toEntity(request)));
    }
    @Transactional
    public PlantillaDTO update(PlantillaRequestDTO request, Long id) {
        Optional<PlantillaMensaje> existe = plantillaRepo.findById(id);
        if (existe.isPresent()) {
            PlantillaMensaje entity = existe.get();

            entity.setTipo(request.getTipo());
            entity.setNombreMeta(request.getNombreMeta());

            return PlantillaMapper.toDTO(plantillaRepo.save(entity));
        }
        else{
            throw new EntidadNoEncontradaException("No existe el plantilla con el id: " + id);
        }
    }
    public void delete(Long id) {
        if (plantillaRepo.findById(id).isPresent()) {
            plantillaRepo.deleteById(id);
        }
        else {
            throw new EntidadNoEncontradaException("No existe el plantilla con el id: " + id);
        }
    }
}
