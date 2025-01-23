package com.hanoli.demojwt.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hanoli.demojwt.entity.Folio;
import com.hanoli.demojwt.entity.FoliosAprobados;
import com.hanoli.demojwt.entity.Imagen;
import com.hanoli.demojwt.services.FileService;
import com.hanoli.demojwt.services.FolioService;
import com.hanoli.demojwt.services.ImagenService;
import com.hanoli.demojwt.sevicesImpl.IExportPdfImpl;
import com.hanoli.shessmat.dto.FileDTO;
import com.hanoli.shessmat.dto.FileMessage;
import com.hanoli.shessmat.dto.FolioDTO;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;*/
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
//@Api
public class FoliosRestController {
	
	@Autowired
    FolioService folioService;
	
	@Autowired
	private IExportPdfImpl exportPdfImpl;
	
	@Autowired
    FileService fileService;
	
	@Autowired
    ImagenService imagenService;
	
	//@ApiOperation(value = "getFolios", notes = "Obtiene todos los folios generados")
	@GetMapping("/listaFolios")
	public List<Folio> getFolios(){
		return folioService.getLista();
	}
	
	
	
	//@ApiOperation(value = "getFolios", notes = "Obtiene todos los folios Aprobados")
	@GetMapping("/listaFoliosAprobados")
	public List<FoliosAprobados> getFoliosAprobados(){
		return folioService.getListaAprobados();
	}
	
	//@ApiOperation(value = "getFolioById", notes = "Obtiene un solo folio de la Base de Datos por su Id")
	@GetMapping("/idFolio/{id}")
	public ResponseEntity<?> getFolioById(@PathVariable Long id){
		Folio folio = folioService.folioId(id);
		
		Map<String,Object> response = new HashMap<>();
		
		if(folio == null) {
			response.put("mensaje", "El Id de folio no existe");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(folio, HttpStatus.OK); 
	}
	
	//@ApiOperation(value = "guardaFolio", notes = "Guarda un folio en la BD")
	@PostMapping("/guardarFolio")
	public ResponseEntity<?> guardaFolio(@RequestBody Folio folio ) {
			
		
		Map<String,Object> response = new HashMap<>();
		
		try {
			folioService.guardaFolio(folio);	
		}catch (Exception e) {
			response.put("mensaje", "Hubo un problema al guardar el folio");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El folio se guardo con exito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	}
	
	/*@ApiOperation(value = "actualiza", notes = "Actualiza un empleado en la BD")
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualiza(@RequestBody Empleado empleado, @PathVariable Long id){
		
		Folio empl = foliosImpl.folioId(id);
		
		Map<String,Object> response = new HashMap<>();
		
		if(empl == null) {
			
			
			response.put("mensaje", "El Id del empleado no existe");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			try {
				
				empl.setNombre(empleado.getNombre());
				empl.setApellidoPat(empleado.getApellidoPat());
				
				foliosImpl.guardaEmpleado(empl);
				
			}catch (Exception e) {
				response.put("mensaje", "Hubo un problema al actualizar el empleado");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "El empleado se actualizo con exito");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}
		
	}*/
	
	//@ApiOperation(value = "Eliminar", notes = "Elimina un Folio en la BD")
	@DeleteMapping("/eliminarFolio/{id}")
	public void eliminar(@PathVariable Long id) {
		
		folioService.Eliminar(id);
		
	}
	
	
	
	@PostMapping("/exportPdf")
	public ResponseEntity<InputStreamResource> exportTermsPdf(@RequestBody Folio folio){
		System.out.println("Recibi peticion de PDF para el folio " + folio.getFolio());
		LocalDate todaysDate = LocalDate.now();
        System.out.println(todaysDate);
        
        List<Imagen> imagen = imagenService.getImagenByFolio(folio.getFolio());
        
		ByteArrayInputStream bais = (ByteArrayInputStream) exportPdfImpl.pdfReport(folio,imagen);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=folio-"+folio.getFolio()+"_"+todaysDate+".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	}
	
	
	 @PostMapping("/upload")
	// public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files")MultipartFile[] files){
	 public ResponseEntity<FileMessage> uploadFiles(@RequestBody FileDTO file){
	        String message = "";
	        
	        System.out.println("Llegue al metodo uploadFiles: " + file.getFolio());
	        System.out.println("Folio: " + file.getFolio());
	        System.out.println("ImagenBase64: " + file.getBase64());
	        
	        Imagen img = new Imagen();
	        img.setFolio(file.getFolio());
	        img.setImagen(file.getBase64());
	        
	        
	    	imagenService.guardaImagen(img);
	        
	        byte[] fileBytes = Base64.getDecoder().decode(file.getBase64());
	        String decodedFile = new String(fileBytes);
	       // System.out.println("Llegue al back");
	       // System.out.println("Se recibio archivo: " + decodedFile);
	        System.out.println("Archivo " + file.getNameFile() + " se subio con exito");
	        
	        /*String originalInput = "test input";
	        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
	        System.out.println(encodedString);
	        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
	        String decodedString = new String(decodedBytes);
	        System.out.println(decodedString);*/
	        try{
	        	
	        	
	        	
	            List<String> fileNames = new ArrayList<>();

	            Arrays.asList(file.getNameFile()).stream().forEach(arch->{
	                fileNames.add(arch);
	            });

	            message = "100";
	            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
	        }catch (Exception e){
	            message = "-1";
	            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
	        }
	    }
	
	
     
	// @ApiOperation(value = "getEndFolio", notes = "Obtiene el ultimo folio generado")
		@GetMapping("/getEndFolio")
		public Integer getEndFolio(){
			return folioService.getEndFolio();
		}
	 
	
	
	// @ApiOperation(value = "getFolioByMarca", notes = "Obtiene el folio marca")
		@GetMapping("/getFolioByMarca/{marca}")
		public ResponseEntity<?>  getFolioByMarca(@PathVariable String marca){
			
		 List<Folio> folio = folioService.getFolioByMarca(marca);
			
			Map<String,Object> response = new HashMap<>();
			
			if(folio == null) {
				response.put("mensaje", "La marca no existe");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity(folio, HttpStatus.OK);
		}
	 
	 
	 	@PostMapping("/folios/getFiltros")
	    public ResponseEntity<?> getFiltros(@RequestBody FolioDTO folioDTO) {

	 		System.out.println("FechaInicio in controller: " + folioDTO.getFechaInicio());
	 		
	        Map<String,Object> response = new HashMap<>();

	        List<Folio> result = folioService.getByFiltros(folioDTO);

	        if(result.isEmpty()) {
	            response.put("mensaje", "No se encontraron resultados");
	            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
	        }

	          return new ResponseEntity(result,HttpStatus.OK);

	    }
	 	
	 	
	 //	@ApiOperation(value = "recibeFolios", notes = "recibe folios seleccionados")
		@PostMapping("/foliosSelect")
		public ResponseEntity<?> foliosSelect(@RequestBody List<Folio> folio ) {
				
	 		System.out.println("El folio se recibio con exito " + folio.size());
	 		
	 		List<FoliosAprobados> folios = new ArrayList<>();
	 		Map<String,Object> response = new HashMap<>();
			
			try {
				
				for (Folio dataFolio : folio) {
					FoliosAprobados foliosAprobados = new FoliosAprobados();
					System.out.println(dataFolio.getFolio());
					System.out.println(dataFolio.getMarca());
					System.out.println(dataFolio.getModelo());
					System.out.println(dataFolio.getNumSerie());
					System.out.println(dataFolio.getTipoEquipo());
					System.out.println(dataFolio.getFecha());
					System.out.println(dataFolio.getCliente().getId());
					
					foliosAprobados.setFolio(dataFolio.getFolio());
					foliosAprobados.setMarca(dataFolio.getMarca());
					foliosAprobados.setModelo(dataFolio.getModelo());
					foliosAprobados.setNumSerie(dataFolio.getNumSerie());
					foliosAprobados.setTipoEquipo(dataFolio.getTipoEquipo());
					foliosAprobados.setFecha(dataFolio.getFecha());
					foliosAprobados.setCliente(1);
					foliosAprobados.setEstatus(0);
					
					folios.add(foliosAprobados);
					
					//folioService.guardaFolioAprobado(foliosAprobados);
				}
				
				for (FoliosAprobados dataFolioAproba : folios) {
					System.out.println("dataFolioAproba.getFolio: " + dataFolioAproba.getFolio());
					System.out.println("dataFolioAproba.getMarca: " + dataFolioAproba.getMarca());
					System.out.println("dataFolioAproba.getModelo: " + dataFolioAproba.getModelo());
					System.out.println("dataFolioAproba.getNumSerie: " + dataFolioAproba.getNumSerie());
					System.out.println("dataFolioAproba.getTipoEquipo: " + dataFolioAproba.getTipoEquipo());
					System.out.println("dataFolioAproba.getFecha: " + dataFolioAproba.getFecha());
					System.out.println("dataFolioAproba.getCliente: " + dataFolioAproba.getCliente());
					System.out.println("dataFolioAproba.getEstatus: " + dataFolioAproba.getEstatus());
					
					folioService.guardaFolioAprobado(dataFolioAproba);
				}
				
				
				//folioService.guardaFolioAprobado(folio);	
			}catch (Exception e) {
				response.put("mensaje", "Hubo un problema al guardar el folio");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("mensaje", "Exito al guardar folios");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
			
		}
	
	
}
