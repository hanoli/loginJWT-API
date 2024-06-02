package com.hanoli.demojwt.sevicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.repository.ClienteRepository;
import com.hanoli.demojwt.User.Role;
import com.hanoli.demojwt.User.User;
import com.hanoli.demojwt.entity.Cliente;
import com.hanoli.demojwt.services.IClienteService;


@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private ClienteRepository clientesDao;

	@Override
	public List<Cliente> getLista() {
		
		return (List<Cliente>) clientesDao.findAll();
	
	}

	@Override
	public Cliente clienteId(Long id) {	
		 return clientesDao.findById(id).orElse(null);
	}
	
	@Override
	public void updateClientebyId(Cliente cliente) {
	
		  clientesDao.updateCliente(
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
	public Cliente guardaCliente(Cliente cliente) {
		return clientesDao.save(cliente);
	}

}
