package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.ClientaMapper;
import org.Marias.BeautyAgenda.dto.ClientaDTO;
import org.Marias.BeautyAgenda.dto.ClientaRequestDTO;
import org.Marias.BeautyAgenda.entity.Clienta;
import org.Marias.BeautyAgenda.repository.ClientaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientaService {
    @Autowired
    private ClientaRepository clientaRepo;



    //metodo para consultar todas la clientas
    public List<ClientaDTO> findAll(){
        return clientaRepo.findAll().stream().map(ClientaMapper::toDTO).collect(Collectors.toList());
    }

    //metodo para buscar una clienta por id
    public Optional<ClientaDTO> findById(Long id){
        return clientaRepo.findById(id).map(ClientaMapper::toDTO);

    }

    //metodo para buscar clienta por nombre
    public List<ClientaDTO> findByNombre(String nombre){
        return  clientaRepo.findByNombreContainingIgnoreCase(nombre).stream()
                .map(ClientaMapper::toDTO).collect(Collectors.toList());

    }

    //metodo para guardar clienta nueva
    public ClientaDTO save(ClientaRequestDTO dto){
        Clienta clienta = ClientaMapper.RqToEntity(dto, LocalDateTime.now(ZoneId.of("America/Mexico_City")));
        return ClientaMapper.toDTO(clientaRepo.save(clienta));
    }
    //metodo para actualizar clienta
    public ClientaDTO update(Long id, ClientaRequestDTO dto){
        Optional<Clienta> existe = clientaRepo.findById(id);
        if(existe.isPresent()){
            Clienta clienta = existe.get();

            clienta.setNombre(dto.getNombre());
            clienta.setTelefono(dto.getTelefono());
            clienta.setFechaNacimiento(dto.getFechaNacimiento());
            clienta.setRecordatorios(dto.isRecordatorios());
            clienta.setMarketing(dto.isMarketing());
            clienta.setNotas(dto.getNotas());
            clientaRepo.save(clienta);
            return ClientaMapper.toDTO(clienta);

        }
        else {
            throw new RuntimeException("Clienta no encontrada");
        }

    }
    //metodo para borrar clienta
    public boolean delete(Long id){
        if(clientaRepo.findById(id).isPresent()){
            clientaRepo.deleteById(id);
            return true;
        }
        else {
            throw new RuntimeException("Cliente no encontrada");
        }
    }


}
