package com.hanoli.demojwt.services;

import java.util.List;

import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Cliente;


public interface IUsuarioService {
	
	public List<Cliente> getLista();

	public Cliente usuarioId(Long Id);
	
	public void updateUsuariobyId(Cliente cliente);
	
	public Cliente guardaUsuario(Cliente cliente);
	
	public void Eliminar(Long id);
	
}
