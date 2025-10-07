package ws.maquila.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ws.maquila.model.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer>{    
}
