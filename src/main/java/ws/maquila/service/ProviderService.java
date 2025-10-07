package ws.maquila.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ws.maquila.repository.ProviderRepository;
import ws.maquila.model.Provider;

@Service
@Transactional
public class ProviderService {
	@Autowired
    private ProviderRepository repository;

    public List<Provider> getAll() {
		return repository.findAll();
	}

	public Provider save(Provider provider) {
		return repository.save(provider);
	}

	public Provider getById(Integer idProvider) {
		return repository.findById(idProvider).get();
	}

	public void delete(Integer idProvider) {
		repository.deleteById(idProvider);
	}    
}
