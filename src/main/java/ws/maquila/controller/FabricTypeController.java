package ws.maquila.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import ws.maquila.dto.FabricTypeDTO;
import ws.maquila.model.FabricType;
import ws.maquila.service.FabricTypeService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@Validated
@RequestMapping("fabrictypes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Fabric types", description = "Provides methods for managing fabric types")
public class FabricTypeController {
	@Autowired
	private FabricTypeService service;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get all fabric types")
	@ApiResponse(responseCode = "200", description = "Found fabric types", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FabricTypeDTO.class))) })
	@GetMapping
	public List<FabricTypeDTO> getAll() {
		List<FabricType> fabricTypes = service.getAll();
		return fabricTypes.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Operation(summary = "Get a fabric type by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fabric type found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = FabricTypeDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid fabric type id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Fabric type not found", content = @Content) })
	@GetMapping("{idFabricType}")
	public ResponseEntity<FabricTypeDTO> getById(@PathVariable @Min(2) Integer idFabricType) {
		return new ResponseEntity<FabricTypeDTO>(convertToDTO(service.getById(idFabricType)), HttpStatus.OK);
	}

	@Operation(summary = "Register a type of fabric")
	@ApiResponse(responseCode = "201", description = "Registered fabric type", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FabricTypeDTO.class))) })
	@PostMapping
	public ResponseEntity<FabricTypeDTO> add(@Valid @RequestBody FabricTypeDTO fabricTypeDTO) {
		FabricTypeDTO savedFabricType = convertToDTO(service.save(convertToEntity(fabricTypeDTO)));
		return new ResponseEntity<>(savedFabricType, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a type of fabric")
	@ApiResponse(responseCode = "200", description = "Updated fabric type", content = {
		@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FabricTypeDTO.class))) })
	@PutMapping("{idFabricType}")
	public ResponseEntity<FabricTypeDTO> update(@RequestBody FabricTypeDTO fabricTypeDTO, @PathVariable Integer idFabricType){
		if(!Objects.equals(idFabricType, fabricTypeDTO.getId())){
            throw new IllegalArgumentException("The provided identifiers do not match");
        }
		FabricType existingFabricType = service.getById(idFabricType);
		existingFabricType.setName(fabricTypeDTO.getName());
		service.save(existingFabricType);
		return new ResponseEntity<>(convertToDTO(existingFabricType), HttpStatus.OK);
	}

	private FabricTypeDTO convertToDTO(FabricType fabricType) {
		return modelMapper.map(fabricType, FabricTypeDTO.class);
	}

	public FabricType convertToEntity(FabricTypeDTO fabricTypeDTO) {
		return modelMapper.map(fabricTypeDTO, FabricType.class);
	}
}
