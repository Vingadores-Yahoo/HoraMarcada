package VingadoresDoYahoo.HoraMarcada.repositories;

import org.springframework.data.repository.CrudRepository;
import VingadoresDoYahoo.HoraMarcada.models.Agendamento;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
    
}