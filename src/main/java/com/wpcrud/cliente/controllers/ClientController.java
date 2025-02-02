package com.wpcrud.cliente.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wpcrud.cliente.dto.ClientDTO;
import com.wpcrud.cliente.services.ClientService;

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

}
