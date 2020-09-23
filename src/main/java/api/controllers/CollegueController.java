package api.controllers;

import java.util.List;

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
	public ResponseEntity<?> getCollegueByNom(@PathVariable String nom) {
		List<Collegue> collegueList = this.collegueRepository.findByNom(nom);
		DtoCollegueResponse response = new DtoCollegueResponse(collegueList);
		return ResponseEntity.ok().body(response);
	}
}
