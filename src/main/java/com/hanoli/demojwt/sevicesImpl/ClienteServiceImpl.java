package com.hanoli.demojwt.sevicesImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.repository.ClienteRepository;
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

	
	
	

}
