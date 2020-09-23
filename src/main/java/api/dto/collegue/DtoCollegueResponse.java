package api.dto.collegue;

import java.util.ArrayList;
import java.util.List;

import api.entites.Collegue;

public class DtoCollegueResponse {
	private List<String> matricules = new ArrayList<>();

	public DtoCollegueResponse(List<Collegue> collegueList) {
		System.out.println("collegueList " + collegueList.size());
		collegueList.forEach(el -> this.matricules.add(el.getMatricule()));
	}

	public List<String> getMatricules() {
		return matricules;
	}

	public void setMatricules(List<String> matricules) {
		this.matricules = matricules;
	}
	
	
}
