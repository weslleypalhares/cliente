package com.wpcrud.cliente.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wpcrud.cliente.dto.ClientDTO;
import com.wpcrud.cliente.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	//CONSULTA POR ID
		@GetMapping(value = "/{id}")
		public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
			ClientDTO dto = service.findById(id);
			return ResponseEntity.ok(dto);
		}
		
		//CONSULTA TODOS PAGINADA
		@GetMapping
		public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
			Page<ClientDTO> dto = service.findAll(pageable);
			return ResponseEntity.ok(dto);
		}
		
		//INSERT
		@PostMapping
		public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
			dto = service.insert(dto);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();//RESPOSTA CUSTOMIZADA '201' COM RESPONSEENTITY
			return ResponseEntity.created(uri).body(dto);
		}
		
		//UPDATE
		@PutMapping(value = "/{id}")
		public ResponseEntity<ClientDTO> update(@PathVariable Long id,@Valid @RequestBody ClientDTO dto) {
			dto = service.update(id, dto);
			return ResponseEntity.ok(dto);
		}
		
		//DELETE POR ID
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}


}
