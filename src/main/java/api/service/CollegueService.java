package api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import api.dto.collegue.DtoCollegueRequest;
import api.dto.collegue.DtoCollegueResponse;
import api.dto.collegue.DtoUpdateCollegueRequest;
import api.entites.Collegue;
import api.exceptions.AdresseEmailExistanteException;
import api.exceptions.CollegueNonTrouveException;
import api.exceptions.CollegueIdException;
import api.repository.CollegueRepository;

@Service
public class CollegueService {
	private CollegueRepository collegueRepository;
	
	public CollegueService(CollegueRepository collegueRepository) {
		this.collegueRepository = collegueRepository;
	}
	
	
	public List<DtoCollegueResponse> getCollegueByMatricule(String nom) throws CollegueNonTrouveException {
		 List<Collegue> collegueList = this.findMatriculeByCollegueNom(nom);
		 return this.createDtoCollegueResponse(collegueList);
	}
	
	public Collegue createCollegue(DtoCollegueRequest dtoRequest) throws AdresseEmailExistanteException {
		Collegue collegue = this.checkSiEmailExiste(dtoRequest);
		return this.collegueRepository.save(collegue);
	}
	
	public Collegue mergeCollegue(DtoUpdateCollegueRequest dtoRequest) throws CollegueIdException {
		Collegue collegue = this.updatePropertiesCollegue(dtoRequest);
		return this.collegueRepository.save(collegue);
	}
	
	private Collegue updatePropertiesCollegue(DtoUpdateCollegueRequest dtoRequest) throws CollegueIdException {
		Collegue collegue = this.findCollegueById(dtoRequest.getId());
		collegue.setDateDeNaissance(dtoRequest.getDateDeNaissance());
		collegue.setEmail(dtoRequest.getEmail());
		collegue.setMatricule(dtoRequest.getMatricule());
		collegue.setNom(dtoRequest.getNom());
		collegue.setPhotoUrl(dtoRequest.getPhotoUrl());
		collegue.setPrenoms(dtoRequest.getPrenoms());
		
		return collegue;
	}
	
	private Collegue findCollegueById(Integer id) throws CollegueIdException {
		return this.collegueRepository.findById(id).orElseThrow(() -> new CollegueIdException("Le collegue n'est pas accessible dans la base de donnée"));
	}
	
	private Collegue checkSiEmailExiste(DtoCollegueRequest dtoRequest) throws AdresseEmailExistanteException {
		return this.findCollegueByEmail(dtoRequest.getEmail()).orElseThrow(() -> new AdresseEmailExistanteException("Cette adresse email est déjà utilisée"));
	}

	private List<DtoCollegueResponse> createDtoCollegueResponse(List<Collegue> collegueList) throws CollegueNonTrouveException {
		if (collegueList.size() != 0) {
			return collegueList.stream().map(collegue -> new DtoCollegueResponse(
					collegue.getMatricule(), 
					collegue.getNom(), 
					collegue.getPrenoms(), 
					collegue.getEmail(), 
					collegue.getDateDeNaissance(), 
					collegue.getPhotoUrl())).collect(Collectors.toList());
		} else {
			throw new CollegueNonTrouveException("Collegue non trouvé");
		}
	}
	
	
	private List<Collegue> findMatriculeByCollegueNom(String nom) {
		return this.collegueRepository.findByNom(nom);
	}
	
	
	private Optional<Collegue> findCollegueByEmail(String email) {
		return this.collegueRepository.findByEmail(email);
	}
}
