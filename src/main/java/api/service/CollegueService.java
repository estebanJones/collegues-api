package api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import api.dto.collegue.DtoCollegueResponse;
import api.entites.Collegue;
import api.repository.CollegueRepository;

@Service
public class CollegueService {
	private CollegueRepository collegueRepository;
	public CollegueService(CollegueRepository collegueRepository) {
		this.collegueRepository = collegueRepository;
	}
	
	
	public List<DtoCollegueResponse> getCollegueByMatricule(String nom) {
		 List<Collegue> collegueList = this.findMatriculeByCollegueNom(nom);
		 return this.createDtoCollegueResponse(collegueList);
	}
	
	private List<DtoCollegueResponse> createDtoCollegueResponse(List<Collegue> collegueList) {
		return collegueList.stream().map(collegue -> new DtoCollegueResponse(
													collegue.getMatricule(), 
													collegue.getNom(), 
													collegue.getPrenoms(), 
													collegue.getEmail(), 
													collegue.getDateDeNaissance(), 
													collegue.getPhotoUrl())).collect(Collectors.toList());

	}
	
	private List<Collegue> findMatriculeByCollegueNom(String nom) {
		return this.collegueRepository.findByNom(nom);
	}
}
