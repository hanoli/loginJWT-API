package com.hanoli.demojwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.hanoli.demojwt.entity.Imagen;
import com.hanoli.demojwt.services.ImagenServiceCustom;

/*import com.example.demo.entity.Cliente;
import com.example.demo.entity.Folio;
import com.example.demo.entity.Imagen;
import com.example.demo.services.FolioServiceCustom;
import com.example.demo.services.ImagenServiceCustom;*/

public interface ImagenRepository extends JpaRepository<Imagen, Long>, ImagenServiceCustom{
	
	
	
	
	@Query("SELECT i FROM Imagen i where i.folio = ?1")
	 List<Imagen> getImagenByFolio(String folio);
	
	

}
