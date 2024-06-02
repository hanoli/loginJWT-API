package com.hanoli.demojwt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanoli.demojwt.User.Role;
import com.hanoli.demojwt.User.User;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clientes")
public class Cliente implements Serializable, UserDetails{
	
	private static final long serialVersionUID = 4629780573695595838L;
	
	
	
	@Id
	//Configuracion para Oracle
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CLI_SEQ")
	//@SequenceGenerator(name = "CLI_SEQ",sequenceName = "cliente_seq",initialValue=1, allocationSize = 1 )
	
	//Configuracion para MySQL
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Long id;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("apellidoPat")
	private String apellidoPat;
	@JsonProperty("apellidoMat")
	private String apellidoMat;
	@JsonProperty("direccion")
	private String direccion;
	@JsonProperty("telefono")
	private String telefono;
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("role")
	private @Enumerated(EnumType.STRING) 
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((role.name())));
    }
    
    
    
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }



	public Cliente() {
	
	}


	
	@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Folio> folios;
	
	
}
