package VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long>{

    List<Agendamento> findByUsuarioId(Long id);

}