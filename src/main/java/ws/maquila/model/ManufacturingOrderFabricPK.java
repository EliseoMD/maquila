package ws.maquila.model;

import java.io.Serializable;
import java.util.Objects;

public class ManufacturingOrderFabricPK implements Serializable {
    private ManufacturingOrder manufacturingOrder;
    private Fabric idFabric;
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManufacturingOrderFabricPK manufacturingOrderFabricPK))
            return false;
        return manufacturingOrder.getId() == manufacturingOrderFabricPK.manufacturingOrder.getId() && Objects.equals(idFabric, manufacturingOrderFabricPK.idFabric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturingOrder, idFabric);
    }

    public ManufacturingOrder getManufacturingOrder() {
        return manufacturingOrder;
    }

    public Fabric getIdFabric() {
        return idFabric;
    }    
}