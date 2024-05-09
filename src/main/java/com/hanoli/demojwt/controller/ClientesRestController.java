package com.hanoli.demojwt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.sevicesImpl.ClienteServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMethod;


@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ClientesRestController {

	@Autowired
	private ClienteServiceImpl clientesImpl;
	
	@GetMapping("/lista")
	public List<Cliente> getClientes(){
		return clientesImpl.getLista();
	}
	
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardaCliente(@RequestBody Cliente empleado ) {
			
		System.out.println("Objeto empleado: " + empleado.getNombre() );
		Map<String,Object> response = new HashMap<>();
		
		try {
			//clientesImpl.guardaCliente(empleado);	
		}catch (Exception e) {
			response.put("mensaje", "Hubo un problema al guardar el cliente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente se guardo con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	}
	
}
