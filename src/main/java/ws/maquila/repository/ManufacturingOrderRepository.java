package ws.maquila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.maquila.model.ManufacturingOrder;

public interface ManufacturingOrderRepository extends JpaRepository<ManufacturingOrder, Integer>{    
}
