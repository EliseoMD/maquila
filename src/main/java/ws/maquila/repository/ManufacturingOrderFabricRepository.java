package ws.maquila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.maquila.model.ManufacturingOrderFabric;
import ws.maquila.model.ManufacturingOrderFabricPK;

public interface ManufacturingOrderFabricRepository extends JpaRepository<ManufacturingOrderFabric, ManufacturingOrderFabricPK>{    
}
