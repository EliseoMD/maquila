package ws.maquila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ws.maquila.repository.ManufacturingOrderFabricRepository;
import ws.maquila.model.ManufacturingOrderFabric;

@Service
@Transactional
public class ManufacturingOrderFabricService {
	@Autowired
    private ManufacturingOrderFabricRepository repository;

    public List<ManufacturingOrderFabric> getAll() {
		return repository.findAll();
	}

	public ManufacturingOrderFabric save(ManufacturingOrderFabric manufacturingOrderFabric) {
		return repository.save(manufacturingOrderFabric);
	}  
}
