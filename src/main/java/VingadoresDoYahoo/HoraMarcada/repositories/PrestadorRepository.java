package VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import java.util.*;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PrestadorRepository extends CrudRepository<Prestador,Long>{
/*
    @Query("select i from Usuario i where i.email = :email and i.senha = :senha")
    public Prestador findByEmail(String email);
*/

    Optional<Prestador> findByUsuarioId(Long id);

    List<Prestador> findByBairro(String bairro);
}