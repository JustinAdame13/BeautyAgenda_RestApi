package org.Marias.BeautyAgenda.service;

import org.Marias.BeautyAgenda.Mapper.ClientaMapper;
import org.Marias.BeautyAgenda.dto.ClientaDTO;
import org.Marias.BeautyAgenda.entity.Clienta;
import org.Marias.BeautyAgenda.repository.ClientaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<ClientaDTO> findByNombre(String nombre){
        return  clientaRepo.findByNombre(nombre).map(ClientaMapper::toDTO);
    }
    //metodo para guardar clienta nueva
    public ClientaDTO save(ClientaDTO clientaDTO){
        Clienta clienta = ClientaMapper.toEntity(clientaDTO);
        Clienta clientaNew = clientaRepo.save(clienta);
        return ClientaMapper.toDTO(clientaNew);
    }
    //metodo para actualizar clienta
    public ClientaDTO update(Long id, ClientaDTO clientaDTO){
        Optional<Clienta> existe = clientaRepo.findById(id);
        if(existe.isPresent()){
            Clienta clienta = existe.get();

            clienta.setNombre(clientaDTO.getNombre());
            clienta.setTelefono(clientaDTO.getTelefono());
            clienta.setFechaNacimiento(clientaDTO.getFechaNacimiento());
            clienta.setRecordatorios(clientaDTO.isRecordatorios());
            clienta.setMarketing(clientaDTO.isMarketing());
            clienta.setNotas(clientaDTO.getNotas());
            clientaRepo.save(clienta);
            return ClientaMapper.toDTO(clienta);

        }
        else {
            throw new RuntimeException("Clienta no encontrada");
        }

    }
    //metodo para borrar clienta
    public String delete(Long id){
        if(clientaRepo.findById(id).isPresent()){
            clientaRepo.deleteById(id);
            return "Cliente Eliminado";
        }
        else {
            throw new RuntimeException("Cliente no encontrada");
        }
    }


}
