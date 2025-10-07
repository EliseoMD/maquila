package ws.maquila.controller;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
import ws.maquila.dto.ManufacturingOrderFabricDTO;
import ws.maquila.dto.ManufacturingOrderRequestDTO;
import ws.maquila.model.Fabric;
import ws.maquila.model.ManufacturingOrder;
import ws.maquila.model.ManufacturingOrderFabric;
import ws.maquila.service.FabricService;
import ws.maquila.service.ManufacturingOrderService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("manufacturingOrders")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Manufacturing orders", description = "Provides methods for managing manufacturing orders")
public class ManufacturingOrderController {
	@Autowired
	private ManufacturingOrderService service;

	@Autowired
	private FabricService fabricService;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get all manufacturing orders")
	@ApiResponse(responseCode = "200", description = "Found manufacturing order", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ManufacturingOrder.class))) })
	@GetMapping
	public List<ManufacturingOrder> getAll() {
		return service.getAll();
	}

	@Operation(summary = "Register a manufacturing order")
	@ApiResponse(responseCode = "201", description = "Registered manufacturing order", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ManufacturingOrder.class)) })
	@PostMapping
	public ResponseEntity<ManufacturingOrder> add(
			@RequestBody ManufacturingOrderRequestDTO manufacturingOrderRequestDTO) {
		ManufacturingOrder savedManufacturingOrder = service.save(convertToEntity(manufacturingOrderRequestDTO));
		return new ResponseEntity<ManufacturingOrder>(savedManufacturingOrder, HttpStatus.CREATED);
	}

	public ManufacturingOrder convertToEntity(ManufacturingOrderRequestDTO manufacturingOrderRequestDTO) {
		List<ManufacturingOrderFabricDTO> manufacturingOrderFabricsDTO = manufacturingOrderRequestDTO
				.getManufacturingOrderFabricsDTO();
		List<ManufacturingOrderFabric> manufacturingOrderFabrics = manufacturingOrderFabricsDTO.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());

		ManufacturingOrder manufacturingOrder = modelMapper.map(manufacturingOrderRequestDTO, ManufacturingOrder.class);
		manufacturingOrder.setManufacturingOrderFabrics(manufacturingOrderFabrics);

		Iterator<ManufacturingOrderFabricDTO> manufacturingOrderFabricDTOIterator = manufacturingOrderFabricsDTO.iterator();
		Iterator<ManufacturingOrderFabric> manufacturingOrderFabricIterator = manufacturingOrderFabrics.iterator();
		while (manufacturingOrderFabricDTOIterator.hasNext()) {
			ManufacturingOrderFabricDTO manufacturingOrderFabricDTO = manufacturingOrderFabricDTOIterator.next();
			ManufacturingOrderFabric manufacturingOrderFabric = manufacturingOrderFabricIterator.next();
			Fabric managedFabric = fabricService.getById(manufacturingOrderFabricDTO.getFabricId());
			manufacturingOrderFabric.setIdFabric(managedFabric);
			manufacturingOrderFabric.setManufacturingOrder(manufacturingOrder);
		}		
		return manufacturingOrder;
	}

	private ManufacturingOrderFabric convertToDTO(ManufacturingOrderFabricDTO manufacturingOrderFabricDTO) {
		return modelMapper.map(manufacturingOrderFabricDTO, ManufacturingOrderFabric.class);
	}
}
