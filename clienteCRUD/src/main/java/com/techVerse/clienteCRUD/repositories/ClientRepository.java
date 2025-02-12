package com.techVerse.clienteCRUD.repositories;

import com.techVerse.clienteCRUD.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
