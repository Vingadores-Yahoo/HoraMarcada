package VingadoresDoYahoo.HoraMarcada.repositories;

import org.springframework.data.repository.CrudRepository;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}