package VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<Prestador,Long>{

    Optional<Prestador> findByUsuarioId(Long id);

    List<Prestador> findByBairro(String bairro);
}