package ws.maquila.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ws.maquila.dto.ProviderDTO;
import ws.maquila.model.Provider;
import ws.maquila.service.ProviderService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("providers")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Providers", description = "Provides methods for managing providers")
public class ProviderController {
	@Autowired
	private ProviderService service;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get all providers")
	@ApiResponse(responseCode = "200", description = "Found provider", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Provider.class))) })
	@GetMapping
	public List<Provider> getAll() {
		return service.getAll();
	}

	@Operation(summary = "Register a provider")
	@ApiResponse(responseCode = "201", description = "Registered provider", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProviderDTO.class))) })
	@PostMapping
	public ResponseEntity<ProviderDTO> add(@RequestBody ProviderDTO providerDTO) {
		ProviderDTO savedProvider = convertToDTO(service.save(convertToEntity(providerDTO)));
		return new ResponseEntity<>(savedProvider, HttpStatus.CREATED);
	}

	private ProviderDTO convertToDTO(Provider provider) {
		return modelMapper.map(provider, ProviderDTO.class);
	}

	public Provider convertToEntity(ProviderDTO providerDTO) {
		return modelMapper.map(providerDTO, Provider.class);
	}
}
