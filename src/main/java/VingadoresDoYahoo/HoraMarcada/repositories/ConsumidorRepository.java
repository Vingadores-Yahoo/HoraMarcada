package VingadoresDoYahoo.HoraMarcada.repositories;

import VingadoresDoYahoo.HoraMarcada.models.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConsumidorRepository extends CrudRepository<Consumidor,Long>{
    
    @Query("select i from Usuario i where i.email = :email and i.senha = :senha")
    public Consumidor findByEmail(String email);
}