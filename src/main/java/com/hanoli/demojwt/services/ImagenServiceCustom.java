/**
 * 
 */
package com.hanoli.demojwt.services;

import java.util.List;

import com.hanoli.demojwt.entity.Imagen;
import com.hanoli.shessmat.dto.FolioDTO;



/**
 * @author hanoli
 *
 */
public interface ImagenServiceCustom {
	
	public List<Imagen> getImagenByFolio(FolioDTO folioDTO);
	
}
