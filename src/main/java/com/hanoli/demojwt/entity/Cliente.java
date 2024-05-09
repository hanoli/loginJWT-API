package com.hanoli.demojwt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 4629780573695595838L;
	
	@Id
	//Configuracion para Oracle
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLI_SEQ")
	//@SequenceGenerator(name = "CLI_SEQ",sequenceName = "cliente_seq",initialValue=1, allocationSize = 1 )
	
	//Configuracion para MySQL
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	private String direccion;
	private String telefono;
	private String correo;
	
	@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Folio> folios;
	
	
	public Cliente() {
		this.folios = new ArrayList<>();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPat() {
		return apellidoPat;
	}
	public void setApellidoPat(String apellidoPat) {
		this.apellidoPat = apellidoPat;
	}
	public String getApellidoMat() {
		return apellidoMat;
	}
	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	public List<Folio> getFolios() {
		return folios;
	}
	public void setFolios(List<Folio> folios) {
		this.folios = folios;
	}
	
	
	

}
