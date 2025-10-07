package ws.maquila.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ws.maquila.dto.FabricRequestDTO;
import ws.maquila.model.Fabric;
import ws.maquila.model.FabricType;
import ws.maquila.service.FabricService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("fabrics")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Fabrics", description = "Provides methods for managing fabrics")
public class FabricController {
	@Autowired
	private FabricService service;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get all fabrics")
	@ApiResponse(responseCode = "200", description = "Found fabrics", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Fabric.class))) })
	@GetMapping
	public List<Fabric> getAll() {
		return service.getAll();
	}

	@Operation(summary = "Get all fabrics with pagination")
	@GetMapping(value = "pagination", params = { "page", "pageSize" })
	public List<Fabric> getAllPaginated(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
		List<Fabric> fabrics = service.getAll(page, pageSize);
		return fabrics;
	}

	@Operation(summary = "Get all fabrics order by price")
	@ApiResponse(responseCode = "200", description = "Found fabrics", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Fabric.class))) })
	@GetMapping("orderByPrice")
	public List<Fabric> getAllOrderByPrice() {
		return service.getAllOrderByPrice();
	}

	@Operation(summary = "Get a fabric by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fabric found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = FabricType.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid fabric id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Fabric not found", content = @Content) })
	@GetMapping("{idFabric}")
	public ResponseEntity<Fabric> getById(@PathVariable Integer idFabric) {
		return new ResponseEntity<Fabric>(service.getById(idFabric), HttpStatus.OK);
	}

	@Operation(summary = "Get all fabrics by composition")
	@ApiResponse(responseCode = "200", description = "Found fabric", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Fabric.class))) })
	@GetMapping("/search/{composition}")
	public List<Fabric> getFabricsByComposition(@PathVariable String composition) {
		return service.getFabricsByComposition(composition);
	}

	@Operation(summary = "Register a fabric")
	@ApiResponse(responseCode = "201", description = "Registered fabric", content = {
		@Content(mediaType = "application/json", schema = @Schema(implementation = FabricRequestDTO.class)) })
	@PostMapping
	public ResponseEntity<FabricRequestDTO> add(@RequestBody FabricRequestDTO fabric) {
		FabricRequestDTO savedFabric = convertToDTO(service.save(convertToEntity(fabric)));
		return new ResponseEntity<FabricRequestDTO>(savedFabric, HttpStatus.CREATED);
	}

	private FabricRequestDTO convertToDTO(Fabric fabric) {
		return modelMapper.map(fabric, FabricRequestDTO.class);
	}

	public Fabric convertToEntity(FabricRequestDTO fabricRequestDTO) {
		return modelMapper.map(fabricRequestDTO, Fabric.class);
	}
}
