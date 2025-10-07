package ws.maquila.dto;

public class FabricRequestDTO {
    private Integer id;
    private String composition;
    private Integer fabricTypeId;
    private Double weight;
    private Double price;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getComposition() {
        return composition;
    }
    public void setComposition(String composition) {
        this.composition = composition;
    }
    public Integer getFabricTypeId() {
        return fabricTypeId;
    }
    public void setFabricTypeId(Integer idFabricType) {
        this.fabricTypeId = idFabricType;
    }
    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }    
}