package VingadoresDoYahoo.HoraMarcada.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import VingadoresDoYahoo.HoraMarcada.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
    
    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email);

    @Query("select j from Usuario j where j.email = :email and j.senha = :senha")
    public Usuario buscarLogin(String email, String senha);
}