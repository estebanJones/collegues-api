package api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.collegue.DtoCollegueRequest;
import api.dto.collegue.DtoCollegueResponse;
import api.dto.collegue.DtoCreationCollegueResponse;
import api.dto.collegue.DtoUpdateCollegueRequest;
import api.entites.Collegue;
import api.exceptions.AdresseEmailExistanteException;
import api.exceptions.CollegueIdException;
import api.exceptions.CollegueNonTrouveException;
import api.service.CollegueService;

@RestController
@RequestMapping("/api")
public class CollegueController {
	private CollegueService collegueService;
	
	public CollegueController(CollegueService collegueService) {
		this.collegueService = collegueService;
	}
	@GetMapping("collegue/{nom}")
	public ResponseEntity<?> getCollegueByMatricule(@PathVariable String nom) throws CollegueNonTrouveException {
		List<DtoCollegueResponse> response = this.collegueService.getCollegueByMatricule(nom);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("collegue/create")
	public ResponseEntity<?> createCollegue(@Valid @RequestBody DtoCollegueRequest dtoRequest, BindingResult resValid) throws AdresseEmailExistanteException {
		if(!resValid.hasErrors()) {
			this.collegueService.createCollegue(dtoRequest);
			DtoCreationCollegueResponse response = new DtoCreationCollegueResponse(dtoRequest.getNom(), 
																				   dtoRequest.getPrenoms(), 
																				   dtoRequest.getDateDeNaissance(), 
																				   dtoRequest.getPhotoUrl());
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.badRequest().body("Une erreur est survenue");
		}
	}
	
	@PutMapping("collegue/update")
	public ResponseEntity<?> updateCollegue(@Valid @RequestBody DtoUpdateCollegueRequest dtoRequest, BindingResult resValid) throws CollegueIdException {
		if(!resValid.hasErrors()) {
			Collegue response1 = this.collegueService.mergeCollegue(dtoRequest);
			return ResponseEntity.ok().body(response1);
		} else {
			return ResponseEntity.badRequest().body("Une erreur est survenue");
		}
	}
}