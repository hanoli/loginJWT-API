package com.hanoli.demojwt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Imagen;
import com.hanoli.demojwt.repository.ImagenRepository;

/*import com.example.demo.dto.FolioDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Folio;
import com.example.demo.entity.FoliosAprobados;
import com.example.demo.entity.Imagen;
import com.example.demo.repository.FolioRepository;
import com.example.demo.repository.FoliosAprobadosRepository;
import com.example.demo.repository.ImagenRepository;*/

@Service
public class ImagenService {
	
	@Autowired
	ImagenRepository imagenRepository;
	
	
	
	/*public List<Folio> getLista(){
		return folioRepository.findAll();
	}

	
	public List<FoliosAprobados> getListaAprobados(){
		return foliosAprobadosRepository.findAll();
	}
	
	public Folio folioId(Long Id) {
		return folioRepository.findById(Id).orElse(null);
	}
	
	public Folio guardaFolio(Folio empleado) {
		return folioRepository.save(empleado);
	}
	
	public FoliosAprobados guardaFolioAprobado(FoliosAprobados folio) {
		return foliosAprobadosRepository.save(folio);
	}
	
	
	public Integer getEndFolio() {
		return folioRepository.getEndFolio();
		
		
	}*/
	
	
	public Imagen guardaImagen(Imagen imagen) {
		return imagenRepository.save(imagen);
	}
	
	public List<Imagen> getImagenByFolio(String folio){
		System.out.println("Estoy en ImagenService " + folio);
		return imagenRepository.getImagenByFolio(folio);
	}
	
	/*public List<Folio> getByFiltros(FolioDTO folioDTO){
		return folioRepository.getByFiltros(folioDTO);
	}

	public void Eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}*/
}
