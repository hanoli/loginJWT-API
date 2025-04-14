/**
 * 
 */
package com.hanoli.demojwt.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.entity.Imagen;



/**
 * @author hanoli
 *
 */
public interface IExportPdf {
	
	public ByteArrayInputStream pdfReport(Folio idFolio,List<Imagen> imagen) ;

}
