package ws.maquila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ws.maquila.repository.FabricRepository;
import ws.maquila.model.Fabric;

@Service
@Transactional
public class FabricService {
	@Autowired
	private FabricRepository repository;

	public List<Fabric> getAll() {
		return repository.findAll();
	}

	public List<Fabric> getAll(int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Fabric> fabrics = repository.findAll(pageReq);
		return fabrics.getContent();
	}

	public List<Fabric> getAllOrderByPrice() {
		return repository.findAll(Sort.by("price"));
	}

	public Fabric save(Fabric fabric) {
		return repository.save(fabric);
	}

	public Fabric getById(Integer idFabric) {
		return repository.findById(idFabric).get();
	}

	public void delete(Integer idFabric) {
		repository.deleteById(idFabric);
	}

	public List<Fabric> getFabricsByComposition(String composition) {
		return repository.getFabricsByCompositionJPQL(composition);
	}
}
