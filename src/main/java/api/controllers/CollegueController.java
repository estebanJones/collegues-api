package api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.collegue.DtoCollegueResponse;
import api.entites.Collegue;
import api.repository.CollegueRepository;

@RestController
@RequestMapping("/api")
public class CollegueController {
	@Autowired
	CollegueRepository collegueRepository;
	@GetMapping("collegue/{nom}")
	public ResponseEntity<?> getCollegueByMatricule(@PathVariable String nom) {
		List<Collegue> collegueList = this.collegueRepository.findByNom(nom);
		List<DtoCollegueResponse> response = collegueList.stream().map(collegue -> new DtoCollegueResponse(
																							collegue.getMatricule(), 
																							collegue.getNom(), 
																							collegue.getPrenoms(), 
																							collegue.getEmail(), 
																							collegue.getDateDeNaissance(), 
																							collegue.getPhotoUrl())).collect(Collectors.toList());

		return ResponseEntity.ok().body(response);
	}
}
