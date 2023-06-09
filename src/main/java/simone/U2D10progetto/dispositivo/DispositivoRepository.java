package simone.U2D10progetto.dispositivo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {
	Optional<Dispositivo> findById(UUID id);
}
