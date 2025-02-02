package com.wpcrud.cliente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wpcrud.cliente.dto.ClientDTO;
import com.wpcrud.cliente.entities.Client;
import com.wpcrud.cliente.repositories.ClientRepository;
import com.wpcrud.cliente.services.exceptions.DatabaseException;
import com.wpcrud.cliente.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		
		//CONSULTA TODOS PAGINADA
		@Transactional(readOnly = true)
		public Page<ClientDTO> findAll(Pageable pageable) {
			Page<Client> result = repository.findAll(pageable);
			return result.map(x -> new ClientDTO(x));
		}
		
		//INSERT	
		@Transactional
		public ClientDTO insert(ClientDTO dto) {
			Client entity = new Client();//PREPARAMOS O OBJETO
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);//OBJETO É SALVO
			return new ClientDTO(entity);//RETORNA O OBJETO SALVO E ATUALIZADO
		}
		
		//UPDATE	
				@Transactional
				public ClientDTO update(Long id, ClientDTO dto) {
					try {
						Client entity = repository.getReferenceById(id);
						copyDtoToEntity(dto, entity);
						entity = repository.save(entity);//OBJETO É SALVO
						return new ClientDTO(entity);//RETORNA O OBJETO SALVO E ATUALIZADO
					}
					catch (EntityNotFoundException e) {
						throw new ResourceNotFoundException("Recurso não encontrado");
					}
				}
		
				
				//DELETE POR ID
				@Transactional(propagation = Propagation.SUPPORTS)
				public void delete(Long id) {
					if(!repository.existsById(id)) {
						throw new ResourceNotFoundException("Recurso não encontrado");
					}
					try {
			        	repository.deleteById(id);    		
				}
			    	catch (DataIntegrityViolationException e) {
			        	throw new DatabaseException("Falha de integridade referencial");
			   	}
				}
		
				
				
		private void copyDtoToEntity(ClientDTO dto, Client entity) {
			entity.setName(dto.getName());
			entity.setCpf(dto.getCpf());
			entity.setIncome(dto.getIncome());
			entity.setBirthDate(dto.getBirthDate());
			entity.setChildren(dto.getChildren());
		}
		

}
