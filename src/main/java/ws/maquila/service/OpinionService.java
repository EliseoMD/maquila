package ws.maquila.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import ws.maquila.model.Opinion;
import ws.maquila.repository.OpinionRepository;

@Service
public class OpinionService {
    @Autowired
    private OpinionRepository repo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Opinion> getAll() {
        return repo.findAll();
    }

    public Opinion getById(Integer id) {
		return repo.findById(id).get();
	}

    public Iterable<Opinion> searchByText(@PathVariable String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("text").regex("^" + name));
        List<Opinion> resources = mongoTemplate.find(query, Opinion.class);
        return resources;
    }

    public Opinion add(Opinion resource) {
        return repo.save(resource);
    }

    public void update(Opinion resource, Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("text", resource.getText()).set("polarity", resource.getPolarity());
        mongoTemplate.updateFirst(query, update, Opinion.class);
    }

    public void delete(Integer id) {
        repo.findById(id).get();
        repo.deleteById(id);
    }

}
