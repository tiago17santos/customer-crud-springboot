package com.techVerse.clienteCRUD.services;

import com.techVerse.clienteCRUD.dtos.ClientDto;
import com.techVerse.clienteCRUD.entities.Client;
import com.techVerse.clienteCRUD.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(client -> new ClientDto(client));
    }

    @Transactional(readOnly = true)
    public ClientDto findById(long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto save(ClientDto clientDto) {
        Client entity = new Client();
        copyDtoToEntity(entity,clientDto);
        entity = clientRepository.save(entity);
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(long id, ClientDto clientDto) {
        try{
            Client entity = clientRepository.findById(id).orElse(null);
            copyDtoToEntity(entity,clientDto);
            entity = clientRepository.save(entity);
            return new ClientDto(entity);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return clientDto;
    }

    public void copyDtoToEntity(Client entity, ClientDto clientDto){
        entity.setName(clientDto.getName());
        entity.setCpf(clientDto.getCpf());
        entity.setIncome(clientDto.getIncome());
        entity.setBirthDate(clientDto.getBirthDate());
        entity.setChildren(clientDto.getChildren());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id) {
        try {
            clientRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
