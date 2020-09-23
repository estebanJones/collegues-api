package api.dto.collegue;

import java.util.List;

import api.entites.Collegue;

public class DtoCollegueResponse {
	private List<String> matricules;

	public DtoCollegueResponse(List<Collegue> collegueList) {
		collegueList.forEach(collegue -> this.matricules.add(collegue.getMatricule()));
	}

	public List<String> getMatricules() {
		return matricules;
	}

	public void setMatricules(List<String> matricules) {
		this.matricules = matricules;
	}
	
	
}
