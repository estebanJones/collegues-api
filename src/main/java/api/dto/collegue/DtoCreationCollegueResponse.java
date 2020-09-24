package api.dto.collegue;

public class DtoCreationCollegueResponse {
	private String nom;
	private String prenoms;
	private String dateDeNaissance;
	private  String photoUrl;
	
	public DtoCreationCollegueResponse(String nom, String prenoms, String dateDeNaissance, String photoUrl) {
		super();
		this.nom = nom;
		this.prenoms = prenoms;
		this.dateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
}
