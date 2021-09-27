package src.main.java.VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

public interface ServicoRepository extends CrudRepository<Servico,Long>{

    Optional<Servico> findByServicoId(Long id);

    List<Servico> findByModalidade(Modalidade modalidade);
}