package ws.maquila.dto;

public class ManufacturingOrderFabricDTO {
    private Integer manufacturingOrderId;
    private Integer fabricId;
    private String color;
    private Double requestedKilograms;
    public Integer getManufacturingOrderId() {
        return manufacturingOrderId;
    }
    public void setManufacturingOrderId(Integer manufacturingOrderId) {
        this.manufacturingOrderId = manufacturingOrderId;
    }
    public Integer getFabricId() {
        return fabricId;
    }
    public void setFabricId(Integer fabricId) {
        this.fabricId = fabricId;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Double getRequestedKilograms() {
        return requestedKilograms;
    }
    public void setRequestedKilograms(Double requestedKilograms) {
        this.requestedKilograms = requestedKilograms;
    }
    
}
