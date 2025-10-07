package ws.maquila.dto;

import java.sql.Date;
import java.util.List;

public class ManufacturingOrderRequestDTO {
    private Integer id;
    private Date requesDate;
    private Date expectedDeliveryDate;
    private Double subtotal;
    private Double total;
    private Integer providerId;
    private List<ManufacturingOrderFabricDTO> manufacturingOrderFabricsDTO;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getRequesDate() {
        return requesDate;
    }
    public void setRequesDate(Date requesDate) {
        this.requesDate = requesDate;
    }
    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }
    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Integer getProviderId() {
        return providerId;
    }
    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
    public List<ManufacturingOrderFabricDTO> getManufacturingOrderFabricsDTO() {
        return manufacturingOrderFabricsDTO;
    }
    public void setManufacturingOrderFabricsDTO(List<ManufacturingOrderFabricDTO> manufacturingOrderFabrics) {
        this.manufacturingOrderFabricsDTO = manufacturingOrderFabrics;
    }

    
}
