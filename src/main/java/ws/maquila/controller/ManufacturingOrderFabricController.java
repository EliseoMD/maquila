package ws.maquila.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ws.maquila.model.ManufacturingOrderFabric;
import ws.maquila.service.ManufacturingOrderFabricService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("manufacturingOrderFabrics")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })
@Tag(name = "Manufacturing order fabrics details", description = "Provides methods for managing details of the fabric manufacturing orders")
public class ManufacturingOrderFabricController {
	@Autowired
	private ManufacturingOrderFabricService service;

	@Operation(summary = "Get all manufacturing order fabrics")
	@ApiResponse(responseCode = "200", description = "Found manufacturing order fabrics", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ManufacturingOrderFabric.class))) })
	@GetMapping
	public List<ManufacturingOrderFabric> getAll() {
		return service.getAll();
	}
}
