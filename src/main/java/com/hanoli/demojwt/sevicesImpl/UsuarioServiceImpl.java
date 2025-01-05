package com.hanoli.demojwt.sevicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.repository.ClienteRepository;
import com.hanoli.demojwt.repository.UsuarioRepository;
import com.hanoli.demojwt.User.Role;
import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.services.IClienteService;
import com.hanoli.demojwt.services.IUsuarioService;


@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository usuariosDao;

	@Override
	public List<Cliente> getLista() {
		
		return (List<Cliente>) usuariosDao.findAll();
	
	}

	@Override
	public Cliente usuarioId(Long id) {	
		 return usuariosDao.findById(id).orElse(null);
	}
	
	@Override
	public void updateUsuariobyId(Cliente cliente) {
	
		usuariosDao.updateCliente(
				 cliente.getId(), 
				 cliente.getNombre(), 
				 cliente.getApellidoPat(), 
				 cliente.getApellidoMat(),
				 cliente.getDireccion(),
				 cliente.getTelefono(),
				 cliente.getUsername()
				 );
	}
	
	@Override
	public Cliente guardaUsuario(Cliente cliente) {
		return usuariosDao.save(cliente);
	}
	
	
	@Override
	public void Eliminar(Long id) {
		usuariosDao.deleteById(id);
		
	}

}
