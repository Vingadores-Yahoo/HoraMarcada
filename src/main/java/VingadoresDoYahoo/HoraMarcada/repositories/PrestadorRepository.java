package VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

public interface PrestadorRepository extends CrudRepository<Prestador,Long>{

    Optional<Prestador> findByUsuarioId(Long id);

    List<Prestador> findByBairro(String bairro);
}