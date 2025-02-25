package com.techVerse.clienteCRUD.services;

import com.techVerse.clienteCRUD.entities.Client;
import com.techVerse.clienteCRUD.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Transactional(readOnly = true)
    public Page<Client> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(client -> client);
    }

    @Transactional(readOnly = true)
    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public Client update(long id, Client client) {
        Client result = clientRepository.findById(id).orElse(null);
        result.setName(client.getName());
        result.setCpf(client.getCpf());
        result.setIncome(client.getIncome());
        result.setBirthDate(client.getBirthDate());
        result.setChildren(client.getChildren());

        return clientRepository.save(result);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(long id) {
        clientRepository.deleteById(id);
    }

}
