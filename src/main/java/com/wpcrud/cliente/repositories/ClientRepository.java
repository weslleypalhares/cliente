package com.wpcrud.cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wpcrud.cliente.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
