package VingadoresDoYahoo.HoraMarcada.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);

    Usuario findByEmail(String email);

}