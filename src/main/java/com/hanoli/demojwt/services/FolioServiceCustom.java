/**
 * 
 */
package com.hanoli.demojwt.services;

import java.util.List;

import com.hanoli.demojwt.entity.Folio;
import com.hanoli.shessmat.dto.FolioDTO;



/**
 * @author hanoli
 *
 */
public interface FolioServiceCustom {
	
	public List<Folio> getByFiltros(FolioDTO folioDTO);
	
}
