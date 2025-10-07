package ws.maquila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ws.maquila.repository.FabricTypeRepository;
import ws.maquila.model.FabricType;

@Service
@Transactional
public class FabricTypeService {
	@Autowired
    private FabricTypeRepository repository;

    public List<FabricType> getAll() {
		return repository.findAll();
	}

	public FabricType save(FabricType fabricType) {
		return repository.save(fabricType);
	}

	public FabricType getById(Integer idFabricType) {
		return repository.findById(idFabricType).get();
	}

	public void delete(Integer idFabricType) {
		repository.deleteById(idFabricType);
	}
}
