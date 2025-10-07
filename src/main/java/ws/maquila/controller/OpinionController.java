package ws.maquila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import ws.maquila.model.Opinion;
import ws.maquila.service.OpinionService;

@RestController
@RequestMapping("opinions")
@Tag(name = "Opinions", description = "Provides methods for managing opinions")
public class OpinionController {
    @Autowired
    private OpinionService service;

    @GetMapping()
    public Iterable<Opinion> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Opinion getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/search/{text}")
    public Iterable<Opinion> searchByText(@PathVariable String text) {
        return service.searchByText(text);
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody Opinion resource) {
        service.add(resource);
        return new ResponseEntity<String>("Saved record", HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Opinion resource, @PathVariable Integer id) {
        service.update(resource, id);
        return new ResponseEntity<String>("Updated record", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<String>("Deleted record", HttpStatus.OK);
    }
}