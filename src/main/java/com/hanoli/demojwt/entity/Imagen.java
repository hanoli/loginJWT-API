package com.hanoli.demojwt.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="imagenes")
public class Imagen implements Serializable{
	
	private static final long serialVersionUID = 4629780573695595838L;
	
	@Id
	//Configuracion para Oracle
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IMG_SEQ")
	//@SequenceGenerator(name = "IMG_SEQ",sequenceName = "imagenes_seq",initialValue=1, allocationSize = 1 )
	
	//Configuracion para MySQL
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String folio;
	private String imagen;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}
	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}
	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	
	

}


