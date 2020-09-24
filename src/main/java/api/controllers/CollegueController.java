package api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.dto.collegue.DtoCollegueResponse;
import api.entites.Collegue;
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
}