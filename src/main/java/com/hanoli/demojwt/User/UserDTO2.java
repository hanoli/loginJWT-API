package com.hanoli.demojwt.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO2 {
	private Long id;
    private String nombre;
	private String apellidoPat;
	private String apellidoMat;
	private String direccion;
	private String telefono;
	private String username;
	
	
	
    

    
    
}
