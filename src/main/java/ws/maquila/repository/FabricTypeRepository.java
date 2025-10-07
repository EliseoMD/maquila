package ws.maquila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.maquila.model.FabricType;

public interface FabricTypeRepository extends JpaRepository<FabricType, Integer>{    
}
