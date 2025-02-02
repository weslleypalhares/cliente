package com.wpcrud.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wpcrud.cliente.dto.ClientDTO;
import com.wpcrud.cliente.entities.Client;
import com.wpcrud.cliente.repositories.ClientRepository;
import com.wpcrud.cliente.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	//CONSULTA POR ID
		@Transactional(readOnly = true)
		public ClientDTO findById(Long id) {
			Client client = repository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Recurso não encontrado"));
			return new ClientDTO(client);
		}

}
