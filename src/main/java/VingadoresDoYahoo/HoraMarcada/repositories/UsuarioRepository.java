package VingadoresDoYahoo.HoraMarcada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}