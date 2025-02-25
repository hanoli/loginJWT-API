package com.hanoli.demojwt.services;

import java.util.List;

import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Usuario;


public interface IUsuarioService {
	
	public List<Usuario> getLista();

	public Usuario usuarioId(Long Id);
	
	public void updateUsuariobyId(Usuario cliente);
	
	public Usuario guardaUsuario(Usuario cliente);
	
	public void Eliminar(Long id);
	
}
