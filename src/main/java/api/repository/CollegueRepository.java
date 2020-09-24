package api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.entites.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {
	public List<Collegue> findByNom(String nom);
	public Optional<Collegue> findByEmail(String email);
}
