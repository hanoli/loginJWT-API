package com.hanoli.demojwt.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.hanoli.demojwt.entity.Cliente;

import jakarta.transaction.Transactional;



public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	@Modifying()
	@Transactional()
    //@Query("update Cliente u set u.firstname=:firstname, u.lastname=:lastname, u.country=:country where u.id = :id")
	@Query("update Cliente u set u.nombre=:nombre, u.apellidoPat=:apellidoPat, u.apellidoMat=:apellidoMat,u.direccion=:direccion, u.telefono=:telefono, u.username=:username where u.id = :id")
	void updateCliente(@Param(value = "id") Long id,@Param(value = "nombre") String nombre,@Param(value = "apellidoPat")String apellidoPat,@Param(value = "apellidoMat")String apellidoMat,
    		@Param(value = "direccion")String direccion,@Param(value = "telefono")String telefono,@Param(value = "username")String username);


	
}
