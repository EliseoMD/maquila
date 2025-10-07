package ws.maquila.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ws.maquila.model.Opinion;

public interface OpinionRepository extends MongoRepository<Opinion, Integer>{    
}
