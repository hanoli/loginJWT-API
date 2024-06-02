package com.hanoli.demojwt.services;

import java.util.List;

import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Cliente;


public interface IClienteService {
	
	public List<Cliente> getLista();

	public Cliente clienteId(Long Id);
	
	public void updateClientebyId(Cliente cliente);
	
	public Cliente guardaCliente(Cliente cliente);
	
}
