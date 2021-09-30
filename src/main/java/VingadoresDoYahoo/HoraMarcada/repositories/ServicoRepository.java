package VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico,Long>{

    //Optional<Servico> findByServicoId(Long id);

    List<Servico> findAllByModalidade(Modalidade modalidade);
}